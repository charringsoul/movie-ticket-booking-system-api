package com.example.mtbs.service.imp;

import com.example.mtbs.dto.ShowRequest;
import com.example.mtbs.dto.ShowResponse;
import com.example.mtbs.entity.*;
import com.example.mtbs.exception.ResourceNotFoundException;
import com.example.mtbs.repository.*;
import com.example.mtbs.service.ShowService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ShowServiceImp implements ShowService {

    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;
    private final ScreenRepository screenRepository;
    private final UserRepository userRepository;
    @Override
    @Transactional
    public ShowResponse createShow(ShowRequest request) {

        // Fetch related entities
        Theater theater = theaterRepository.findById(request.theaterId())
                .orElseThrow(() -> new ResourceNotFoundException("Theater not found: " + request.theaterId()));

        Screen screen = screenRepository.findById(request.screenId())
                .orElseThrow(() -> new ResourceNotFoundException("Screen not found: " + request.screenId()));

        Movie movie = movieRepository.findById(request.movieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found: " + request.movieId()));

        // Convert start time from milliseconds
        Instant startTime = Instant.ofEpochMilli(request.startTime());

        // Calculate end time by adding movie runtime in minutes
        long movieRuntimeInMinutes = movie.getRuntime();  // runtime is now a long
        Instant endTime = startTime.plusSeconds(movieRuntimeInMinutes * 60);  // convert minutes to seconds

        // Check for time conflict
        boolean conflictExists = showRepository.existsByScreenAndTimeConflict(screen, startTime, endTime);
        if (conflictExists) {
            throw new IllegalStateException("Time slot is already occupied for this screen.");
        }

        // Fetch the user who created the show
        UserDetail user = userRepository.findByEmail(request.createdBy())
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + request.createdBy()));

        // Create and populate the show
        Show show = new Show();
        show.setMovie(movie);
        show.setScreen(screen);
        show.setTheater(theater);
        show.setStartTime(startTime);
        show.setEndTime(endTime);
        show.setCreatedBy(user);
        Instant now = Instant.now();
        show.setCreatedAt(now);
        show.setUpdatedAt(now);

        // Save and return response
        Show savedShow = showRepository.save(show);

        return new ShowResponse(
                savedShow.getShowId(),
                movie.getMovieId(),
                screen.getScreenId(),
                theater.getTheaterId(),
                savedShow.getStartTime().toEpochMilli(),
                savedShow.getEndTime().toEpochMilli()
        );
    }



}

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

    @Override
    @Transactional
    public ShowResponse createShow(String theaterId, String screenId, ShowRequest request) {

        Theater theater = theaterRepository.findById(theaterId)
                .orElseThrow(() -> new ResourceNotFoundException("Theater not found with ID: " + theaterId));

        Screen screen = screenRepository.findById(screenId)
                .orElseThrow(() -> new ResourceNotFoundException("Screen not found with ID: " + screenId));

        Movie movie = movieRepository.findById(request.movieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with ID: " + request.movieId()));

        Instant startTime = Instant.ofEpochMilli(request.startTime());
        Instant endTime = startTime.plus(movie.getRuntime());

        // Check for conflicting shows
        // Check for conflicting shows
        boolean conflictExists = showRepository.existsByScreenAndTimeConflict(screen, startTime, endTime);

        if (conflictExists) {
            throw new IllegalStateException("Time slot is already occupied for this screen.");
        }


        Show show = new Show();
        show.setMovie(movie);
        show.setScreen(screen);
        show.setTheater(theater);
        show.setStartTime(startTime);
        show.setEndTime(endTime);

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

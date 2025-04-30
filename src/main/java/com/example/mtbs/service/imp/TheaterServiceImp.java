package com.example.mtbs.service.imp;

import com.example.mtbs.dto.TheaterRequest;
import com.example.mtbs.dto.TheaterResponse;
import com.example.mtbs.entity.Theater;
import com.example.mtbs.entity.Theater_Owner;
import com.example.mtbs.exception.UnauthorizedException;
import com.example.mtbs.exception.UserNotFoundException;
import com.example.mtbs.repository.TheaterRepository;
import com.example.mtbs.repository.UserRepository;
import com.example.mtbs.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TheaterServiceImp implements TheaterService {

    private final UserRepository userRepository;
    private final TheaterRepository theaterRepository;

    @Override
    public TheaterResponse createTheater(TheaterRequest request, String email) {
        Theater_Owner theaterOwner = getTheaterOwnerByEmail(email);

        Theater theater = new Theater();
        theater.setName(request.getName());
        theater.setAddress(request.getAddress());
        theater.setCity(request.getCity());
        theater.setLandmark(request.getLandmark());
        theater.setCreatedAt(Instant.now());
        theater.setUpdatedAt(Instant.now());
        theater.setCreatedByEmail(theaterOwner.getEmail());
        theater.setCreatedBy(theaterOwner);

        Theater savedTheater = theaterRepository.save(theater);

        return mapToTheaterResponse(savedTheater);
    }

    @Override
    public TheaterResponse updateTheater(String theaterId, String email, TheaterRequest request) {
        Theater theater = getTheaterById(theaterId);
        validateTheaterOwnership(theater, email);

        theater.setName(request.getName());
        theater.setAddress(request.getAddress());
        theater.setCity(request.getCity());
        theater.setLandmark(request.getLandmark());
        theater.setUpdatedAt(Instant.now());

        Theater updatedTheater = theaterRepository.save(theater);

        return mapToTheaterResponse(updatedTheater);
    }

    @Override
    public void deleteTheater(String theaterId, String email) {
        Theater theater = getTheaterById(theaterId);
        validateTheaterOwnership(theater, email);

        theater.setDeleted(true);
        theater.setDeletedAt(Instant.now());

        theaterRepository.save(theater);
    }

    @Override
    public List<TheaterResponse> getTheatersByOwnerEmail(String email) {
        // No need to fetch Theater_Owner here

        List<Theater> theaters = theaterRepository.findByCreatedByEmailAndDeletedFalse(email);

        return theaters.stream()
                .map(this::mapToTheaterResponse)
                .collect(Collectors.toList());
    }


    // --- Private Helper Methods ---

    private Theater_Owner getTheaterOwnerByEmail(String email) {
        return (Theater_Owner) userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User", "email", email));
    }

    private Theater getTheaterById(String theaterId) {
        return theaterRepository.findById(theaterId)
                .orElseThrow(() -> new RuntimeException("Theater not found"));
    }

    private void validateTheaterOwnership(Theater theater, String email) {
        if (!theater.getCreatedByEmail().equals(email)) {
            throw new UnauthorizedException("User is not the owner of the theater");
        }
    }

    private TheaterResponse mapToTheaterResponse(Theater theater) {
        return new TheaterResponse(
                theater.getTheaterId(),
                theater.getName(),
                theater.getAddress(),
                theater.getCity(),
                theater.getLandmark(),
                theater.getCreatedByEmail()
        );
    }
}

package com.example.mtbs.service.imp;

import com.example.mtbs.dto.*;
import com.example.mtbs.entity.*;
import com.example.mtbs.exception.ResourceNotFoundException;
import com.example.mtbs.repository.*;
import com.example.mtbs.service.ScreenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScreenServiceImp implements ScreenService {

    private final TheaterRepository theaterRepository;
    private final ScreenRepository screenRepository;
    private final SeatRepository seatRepository;

    @Override
    @Transactional
    public ScreenResponse addScreenToTheater(String theaterId, ScreenRequest request) {
        Theater theater = theaterRepository.findById(theaterId)
                .orElseThrow(() -> new ResourceNotFoundException("Theater not found with ID: " + theaterId));

        Screen screen = new Screen();
        screen.setScreenName(request.screenName());
        screen.setCapacity(request.capacity());
        screen.setNoOfRows(request.noOfRows());
        screen.setTheater(theater);

        int capacity = request.capacity();
        int noOfRows = request.noOfRows();
        int seatsPerRow = capacity / noOfRows;
        int remainder = capacity % noOfRows;

        List<Seat> seats = new ArrayList<>();
        char rowChar = 'A';

        for (int row = 0; row < noOfRows; row++) {
            int seatsInThisRow = seatsPerRow + (row < remainder ? 1 : 0);
            for (int seatNum = 1; seatNum <= seatsInThisRow; seatNum++) {
                Seat seat = new Seat();
                seat.setSeatName(rowChar + String.valueOf(seatNum));
                seat.setScreen(screen);
                seats.add(seat);
            }
            rowChar++;
        }

        screen.setSeats(seats);
        Screen savedScreen = screenRepository.save(screen);
        TheaterResponse theaterResponse = new TheaterResponse(
                theater.getTheaterId(),
                theater.getName(),
                theater.getAddress(),
                theater.getCity(),
                theater.getLandmark(),
                theater.getCreatedByEmail()
        );

        List<SeatResponse> seatResponses = savedScreen.getSeats().stream()
                .map(seat -> new SeatResponse(seat.getSeatId(), seat.getSeatName()))
                .collect(Collectors.toList());

        return new ScreenResponse(
                savedScreen.getScreenId(),
                savedScreen.getScreenName(),
                savedScreen.getCapacity(),
                savedScreen.getNoOfRows(),
                seatResponses,
                theaterResponse
        );
    }

    @Override
    public ScreenResponse getScreenById(String screenId) {
        Screen screen = screenRepository.findById(screenId)
                .orElseThrow(() -> new ResourceNotFoundException("Screen not found"));

        List<SeatResponse> seatResponses = screen.getSeats().stream()
                .map(seat -> new SeatResponse(seat.getSeatId(), seat.getSeatName()))
                .collect(Collectors.toList());

        Theater theater = screen.getTheater();

        TheaterResponse theaterResponse = new TheaterResponse(
                theater.getTheaterId(),
                theater.getName(),
                theater.getAddress(),
                theater.getCity(),
                theater.getLandmark(),
                theater.getCreatedByEmail()
        );

        return new ScreenResponse(
                screen.getScreenId(),
                screen.getScreenName(),
                screen.getCapacity(),
                screen.getNoOfRows(),
                seatResponses,
                theaterResponse
        );
    }
}

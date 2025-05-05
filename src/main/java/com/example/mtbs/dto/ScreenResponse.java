package com.example.mtbs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class ScreenResponse {
    private String screenId;
    private String screenName;
    private int capacity;
    private int noOfRows;
    private List<SeatResponse> seats;
    private TheaterResponse theater;
}

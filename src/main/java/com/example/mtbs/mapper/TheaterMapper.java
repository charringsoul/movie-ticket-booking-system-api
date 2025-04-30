package com.example.mtbs.mapper;

import com.example.mtbs.dto.TheaterResponse;
import com.example.mtbs.entity.Theater;

public class TheaterMapper {

    public static TheaterResponse toResponse(Theater theater) {
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

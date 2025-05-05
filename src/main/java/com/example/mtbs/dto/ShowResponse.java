package com.example.mtbs.dto;

public record ShowResponse(
        String showId,
        String movieId,
        String screenId,
        String theaterId,
        long startTime,
        long endTime
) {}

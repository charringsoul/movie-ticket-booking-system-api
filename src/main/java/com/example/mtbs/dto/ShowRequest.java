package com.example.mtbs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record ShowRequest(

        @NotBlank(message = "Movie ID is required")
        String movieId,

        @NotNull(message = "Start time is required")
        Long startTime,  // Start time in milliseconds

        @NotBlank(message = "Created by (User ID or Email) is required")
        String createdBy, // The creator (email or user ID)

        @NotBlank(message = "Screen ID is required")
        String screenId, // Screen ID where the show will be held

        @NotBlank(message = "Theater ID is required")
        String theaterId // Theater ID where the show will be hosted
) {}

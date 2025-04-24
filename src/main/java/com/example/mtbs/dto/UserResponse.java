package com.example.mtbs.dto;

import com.example.mtbs.enums.Role;


import java.time.Instant;
import java.time.LocalDate;

public record UserResponse(
        String userId,
        String username,
        String email,
        String phoneNumber,
        LocalDate dateOfBirth,
        Role userRole,
        Instant createdAt,
        Instant updatedAt
) {
}

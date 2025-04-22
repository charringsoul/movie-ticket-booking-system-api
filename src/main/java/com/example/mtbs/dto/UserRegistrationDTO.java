package com.example.mtbs.dto;

import com.example.mtbs.enums.Role;
import java.time.LocalDate;

public record UserRegistrationDTO(
        String username,
        String email,
        String password,
        String phoneNumber,
        LocalDate dateOfBirth,
        Role userRole
) {}

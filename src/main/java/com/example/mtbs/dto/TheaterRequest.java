package com.example.mtbs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TheaterRequest {

        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name cannot exceed 100 characters")
        private String name;

        @NotBlank(message = "Address is required")
        @Size(max = 200, message = "Address cannot exceed 200 characters")
        private String address;

        @NotBlank(message = "City is required")
        private String city;

        @Size(max = 100, message = "Landmark cannot exceed 100 characters")
        private String landmark;



}

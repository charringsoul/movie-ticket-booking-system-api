package com.example.mtbs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TheaterResponse {
    private String theaterId;
    private String name;
    private String address;
    private String city;
    private String landmark;
    private String createdBy;
}

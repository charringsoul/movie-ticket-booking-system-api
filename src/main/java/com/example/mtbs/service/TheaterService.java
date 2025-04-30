package com.example.mtbs.service;


import com.example.mtbs.dto.TheaterRequest;
import com.example.mtbs.dto.TheaterResponse;
import com.example.mtbs.entity.Theater;

import java.util.List;


public interface TheaterService {
    TheaterResponse createTheater(TheaterRequest request, String email);
    TheaterResponse updateTheater(String theaterId, String email, TheaterRequest request);
    void deleteTheater(String theaterId, String email);
    List<TheaterResponse> getTheatersByOwnerEmail(String email);
}

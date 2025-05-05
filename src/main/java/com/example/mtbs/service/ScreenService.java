package com.example.mtbs.service;

import com.example.mtbs.dto.ScreenResponse;
import com.example.mtbs.dto.ScreenRequest;

public interface ScreenService {
    ScreenResponse addScreenToTheater(String theaterId, ScreenRequest request);
    ScreenResponse getScreenById(String screenId);
}


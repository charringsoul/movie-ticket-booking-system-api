package com.example.mtbs.service;

import com.example.mtbs.dto.ShowRequest;
import com.example.mtbs.dto.ShowResponse;

public interface ShowService {
    ShowResponse createShow(String theaterId, String screenId, ShowRequest request);
}

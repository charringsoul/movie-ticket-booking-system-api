package com.example.mtbs.service;

import com.example.mtbs.dto.ShowRequest;
import com.example.mtbs.dto.ShowResponse;

public interface ShowService {
    ShowResponse createShow(ShowRequest request);
}

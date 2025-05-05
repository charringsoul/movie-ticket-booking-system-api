package com.example.mtbs.controller;

import com.example.mtbs.dto.ShowRequest;
import com.example.mtbs.dto.ShowResponse;
import com.example.mtbs.service.ShowService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/shows")
@RequiredArgsConstructor
@Validated
public class ShowController {

    private final ShowService showService;

    @PostMapping("/{theaterId}/{screenId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ShowResponse createShow(
            @PathVariable String theaterId,
            @PathVariable String screenId,
            @Valid @RequestBody ShowRequest request) {
        return showService.createShow(theaterId, screenId, request);
    }
}

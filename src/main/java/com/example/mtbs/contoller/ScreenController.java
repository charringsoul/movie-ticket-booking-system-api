package com.example.mtbs.contoller;

import com.example.mtbs.dto.ScreenResponse;
import com.example.mtbs.dto.ScreenRequest;
import com.example.mtbs.service.imp.ScreenServiceImp;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/theater")
@AllArgsConstructor

public class ScreenController {

    private final ScreenServiceImp screenServiceImp;

    @PostMapping("/{theaterId}/screens")
    public ResponseEntity<ScreenResponse> addScreenToTheater(
            @PathVariable String theaterId,
            @RequestBody @Valid ScreenRequest request
    ) {
        ScreenResponse response = screenServiceImp.addScreenToTheater(theaterId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{screenId}")
    public ResponseEntity<ScreenResponse> getScreenById(@PathVariable String screenId) {
        ScreenResponse screenResponse = screenServiceImp.getScreenById(screenId);
        return ResponseEntity.ok(screenResponse);
    }

}

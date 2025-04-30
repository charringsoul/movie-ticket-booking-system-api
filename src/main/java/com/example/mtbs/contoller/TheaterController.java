package com.example.mtbs.contoller;

import com.example.mtbs.dto.TheaterRequest;
import com.example.mtbs.dto.TheaterResponse;
import com.example.mtbs.service.TheaterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theater")
@RequiredArgsConstructor
public class TheaterController {

    private final TheaterService theaterService;

    @PostMapping("/{email}")
    public ResponseEntity<TheaterResponse> createTheater(
            @PathVariable String email,
            @Valid @RequestBody TheaterRequest request) {

        TheaterResponse createdTheater = theaterService.createTheater(request, email);
        return ResponseEntity.ok(createdTheater);
    }

    @PutMapping("/{theaterId}/{email}")
    public ResponseEntity<TheaterResponse> updateTheater(
            @PathVariable String theaterId,
            @PathVariable String email,
            @Valid @RequestBody TheaterRequest request) {

        TheaterResponse updatedTheater = theaterService.updateTheater(theaterId, email, request);
        return ResponseEntity.ok(updatedTheater);
    }

    @DeleteMapping("/{theaterId}/{email}")
    public ResponseEntity<String> deleteTheater(
            @PathVariable String theaterId,
            @PathVariable String email) {

        theaterService.deleteTheater(theaterId, email);
        return ResponseEntity.ok("Theater deleted successfully.");
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<TheaterResponse>> getTheatersByOwnerEmail(
            @PathVariable String email) {

        List<TheaterResponse> theaters = theaterService.getTheatersByOwnerEmail(email);
        return ResponseEntity.ok(theaters);
    }
}

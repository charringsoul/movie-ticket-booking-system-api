package com.example.mtbs.controller;

import com.example.mtbs.dto.UserRegistrationDTO;
import com.example.mtbs.entity.UserDetail;
import com.example.mtbs.service.UserService;
import com.example.mtbs.utility.ResponseFactory;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final ResponseFactory responseFactory;

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        // Map the DTO to the entity (User or TheaterOwner)
        UserDetail savedUser = userService.saveUser(userRegistrationDTO);

        return responseFactory.successResponse(savedUser, "User registered successfully", HttpStatus.OK);
    }
}

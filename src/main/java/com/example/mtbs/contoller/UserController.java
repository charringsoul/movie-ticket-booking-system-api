package com.example.mtbs.contoller;

import com.example.mtbs.dto.UserRegistrationDTO;
import com.example.mtbs.entity.UserDetail;
import com.example.mtbs.service.UserService;
import com.example.mtbs.utility.ResponseFactory;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@AllArgsConstructor

public class UserController {

    private final UserService userService;
    private final ResponseFactory responseFactory;

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> registerUser(@Valid @RequestBody  UserRegistrationDTO userRegistrationDTO) {
        // Map the DTO to the entity (User or TheaterOwner)
        UserDetail savedUser = userService.saveUser(userRegistrationDTO);

        return responseFactory.successResponse(savedUser, "User registered successfully", HttpStatus.OK);
    }


    @PutMapping("/update/{email}")
    public ResponseEntity<?> updateUser(
            @PathVariable String email,
            @Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {

        UserDetail updatedUser = userService.updateUser(email, userRegistrationDTO);
        return responseFactory.successResponse(updatedUser, "User updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam String email) {
        userService.softDeleteUserByEmail(email);
        return ResponseEntity.ok("User with email " + email + " has been soft deleted.");
    }
}

package com.example.mtbs.service.imp;

import com.example.mtbs.dto.UserRegistrationDTO;
import com.example.mtbs.entity.User;
import com.example.mtbs.entity.Theater_Owner;
import com.example.mtbs.entity.UserDetail;
import com.example.mtbs.enums.Role;
import com.example.mtbs.execption.handler.UserAlreadyExistsException;
import com.example.mtbs.execption.handler.UserNotInsertedException;
import com.example.mtbs.repository.UserRepository;
import com.example.mtbs.service.UserService;
import com.example.mtbs.mapper.UserRegistrationMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final UserRegistrationMapper userRegistrationMapper; // Inject the mapper

    @Override
    public UserDetail saveUser(UserRegistrationDTO userRegistrationDTO) {
        // Check if email or phone number already exists
        if (userRepository.existsByEmail(userRegistrationDTO.email()) ||
                userRepository.existsByPhoneNumber(userRegistrationDTO.phoneNumber())) {
            throw new UserAlreadyExistsException("User already exists with same email or phone number");
        }

        // Map the DTO to the respective entity (User or TheaterOwner)
        UserDetail userDetail = userRegistrationMapper.mapToEntity(userRegistrationDTO);

        try {
            // Save the user instance (either User or TheaterOwner)
            return userRepository.save(userDetail);
        } catch (Exception e) {
            throw new UserNotInsertedException("Failed to register user");
        }
    }

    @Override
    public List<UserDetail> getAllUsers() {
        return userRepository.findAll();
    }
}

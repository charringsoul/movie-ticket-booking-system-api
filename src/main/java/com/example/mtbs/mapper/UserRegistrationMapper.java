package com.example.mtbs.mapper;

import com.example.mtbs.dto.UserRegistrationDTO;
import com.example.mtbs.entity.Theater_Owner;
import com.example.mtbs.entity.User;
import com.example.mtbs.entity.UserDetail;
import com.example.mtbs.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationMapper {

    // Public method to map DTO to Entity
    public UserDetail mapToEntity(UserRegistrationDTO dto) {
        return switch (dto.userRole()) {
            case USER -> User.builder()
                    .username(dto.username())
                    .email(dto.email())
                    .password(dto.password())
                    .phoneNumber(dto.phoneNumber())
                    .dateOfBirth(dto.dateOfBirth())
                    .userRole(Role.USER)
                    .build();

            case THEATER_OWNER -> Theater_Owner.builder()
                    .username(dto.username())
                    .email(dto.email())
                    .password(dto.password())
                    .phoneNumber(dto.phoneNumber())
                    .dateOfBirth(dto.dateOfBirth())
                    .userRole(Role.THEATER_OWNER)
                    .build();
        };
    }
}

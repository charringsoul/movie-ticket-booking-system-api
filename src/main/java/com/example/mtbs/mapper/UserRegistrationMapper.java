package com.example.mtbs.mapper;

import com.example.mtbs.dto.UserRegistrationDTO;
import com.example.mtbs.entity.Theater_Owner;
import com.example.mtbs.entity.User;
import com.example.mtbs.entity.UserDetail;
import com.example.mtbs.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationMapper {

    public UserDetail mapToEntity(UserRegistrationDTO dto) {
        if (dto.userRole() == Role.THEATER_OWNER) {
            return Theater_Owner.builder()
                    .username(dto.username())
                    .email(dto.email())
                    .password(dto.password())
                    .phoneNumber(dto.phoneNumber())
                    .userRole(dto.userRole())
                    .dateOfBirth(dto.dateOfBirth())
                    .build();
        } else {
            return UserDetail.builder()
                    .username(dto.username())
                    .email(dto.email())
                    .password(dto.password())
                    .phoneNumber(dto.phoneNumber())
                    .userRole(dto.userRole())
                    .dateOfBirth(dto.dateOfBirth())
                    .build();
        }
    }
}

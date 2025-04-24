package com.example.mtbs.mapper;

import com.example.mtbs.dto.UserResponse;
import com.example.mtbs.entity.Theater_Owner;
import com.example.mtbs.entity.User;
import com.example.mtbs.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper {

    public UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getDateOfBirth(),
                user.getUserRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public UserResponse toUserResponse(Theater_Owner owner) {
        return new UserResponse(
                owner.getUserId(),
                owner.getUsername(),
                owner.getEmail(),
                owner.getPhoneNumber(),
                owner.getDateOfBirth(),
                owner.getUserRole(),
                owner.getCreatedAt(),
                owner.getUpdatedAt()
        );
    }

    // Optional: Unified method based on role
    public UserResponse toUserResponse(Object entity, Role role) {
        return switch (role) {
            case USER -> toUserResponse((User) entity);
            case THEATER_OWNER -> toUserResponse((Theater_Owner) entity);
        };
    }
}

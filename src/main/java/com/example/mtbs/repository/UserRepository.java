package com.example.mtbs.repository;

import com.example.mtbs.entity.UserDetail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDetail, String> {

    Optional<UserDetail> findByEmail(String email);

    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);

    Optional<Object> findByEmailAndIsDeletedFalse(String email);
}

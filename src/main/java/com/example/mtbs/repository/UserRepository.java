package com.example.mtbs.repository;

import com.example.mtbs.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDetail, String> {

    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}

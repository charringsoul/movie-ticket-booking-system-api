package com.example.mtbs.repository;


import com.example.mtbs.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, String> {

    List<Theater> findByCreatedByEmailAndDeletedFalse(String email);

    Optional<Theater> findById(String id);
}


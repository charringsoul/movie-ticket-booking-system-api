package com.example.mtbs.repository;

import com.example.mtbs.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, String> {
}

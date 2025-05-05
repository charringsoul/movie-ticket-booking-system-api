package com.example.mtbs.repository;

import com.example.mtbs.entity.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository  extends JpaRepository<Screen, String> {}


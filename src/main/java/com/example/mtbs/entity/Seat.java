package com.example.mtbs.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String seatId;

    private String seatName;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;
    private Instant createdAt;
    private Instant updatedAt;

    // Getters and setters
}

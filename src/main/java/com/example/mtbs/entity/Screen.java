package com.example.mtbs.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;


@Setter
@Getter
@Entity
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String screenId;

    private String screenName;
    private int capacity;
    private int noOfRows;

    @ManyToOne
    private Theater theater;

    private Instant createdAt;
    private Instant updatedAt;
    // Getters and setters
    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Seat> seats;

}

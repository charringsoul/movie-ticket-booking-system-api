package com.example.mtbs.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String showId;

    @ManyToOne
    private Screen screen;

    @ManyToOne
    private Theater theater;

    @ManyToOne
    private Movie movie;

    private Instant startTime;

    private Instant endTime;


}

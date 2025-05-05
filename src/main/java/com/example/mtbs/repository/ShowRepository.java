package com.example.mtbs.repository;

import com.example.mtbs.entity.Screen;
import com.example.mtbs.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;

public interface ShowRepository extends JpaRepository<Show, String> {

    @Query("""
        SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END
        FROM Show s
        WHERE s.screen = :screen
        AND (
            (s.startTime < :endTime AND s.endTime > :startTime)
        )
    """)
    boolean existsByScreenAndTimeConflict(Screen screen, Instant startTime, Instant endTime);
}

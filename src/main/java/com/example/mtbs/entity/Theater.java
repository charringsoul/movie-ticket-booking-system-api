package com.example.mtbs.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Setter
@Getter
public class Theater {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private String theaterId;

        private String name;
        private String address;
        private String city;
        private String landmark;

        private Instant createdAt;
        private Instant updatedAt;

        // This will store the email for the FK relationship
        private String createdByEmail;

        // Many-to-one relationship to Theater_Owner
        @ManyToOne(fetch = FetchType.LAZY)
        private Theater_Owner createdBy;


        private Boolean deleted = false;

        private Instant deletedAt;

        // Getters and Setters
}

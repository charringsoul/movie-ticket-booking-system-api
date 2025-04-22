package com.example.mtbs.entity;

import com.example.mtbs.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role userRole;

    private LocalDate dateOfBirth;

    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;
}

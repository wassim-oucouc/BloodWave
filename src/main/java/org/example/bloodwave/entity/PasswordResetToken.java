package org.example.bloodwave.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@Data
public class PasswordResetToken {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String token;

    private LocalDateTime expiryDate;

    @OneToOne
    private Utilisateur user;

    public PasswordResetToken() {

    }
}

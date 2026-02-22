package org.example.bloodwave.domain.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String message;
    private Boolean lue;
    private LocalDateTime dateEnvoi;

    @ManyToOne
    private Utilisateur utilisateur;
}

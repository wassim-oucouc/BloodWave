package org.example.bloodwave.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.bloodwave.enumeration.RoleType;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Utilisateur{

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String telephone;
    private String adresse;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    private Boolean actif;

    private LocalDateTime dateCreation;



}
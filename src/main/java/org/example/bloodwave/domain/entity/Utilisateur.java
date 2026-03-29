package org.example.bloodwave.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.bloodwave.domain.enumeration.RoleType;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Utilisateur{

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
      @Column(name = "image_profile")
    private String imageProfile;
    private String email;
    private String motDePasse;
    private String telephone;
    private String adresse;
    private String ville;


 @Enumerated(EnumType.STRING)
    private RoleType role;

    private Boolean actif;

    private LocalDateTime dateCreation;



}
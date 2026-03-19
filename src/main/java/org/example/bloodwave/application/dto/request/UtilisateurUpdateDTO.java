package org.example.bloodwave.application.dto.request;

import lombok.Data;

@Data
public class UtilisateurUpdateDTO {
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;
    private String ville;
    private Boolean actif;
}
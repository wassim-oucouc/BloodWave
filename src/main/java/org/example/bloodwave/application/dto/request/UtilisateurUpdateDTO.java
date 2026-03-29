package org.example.bloodwave.application.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class UtilisateurUpdateDTO {
    private String nom;
    private String prenom;
    private String email;
    @JsonAlias({"image_profile", "imageProfile"})
    private String imageProfile;
    private String telephone;
    private String adresse;
    private String ville;
    private Boolean actif;
}
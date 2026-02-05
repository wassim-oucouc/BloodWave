package org.example.bloodwave.application.dto.response;

import lombok.Data;
import org.example.bloodwave.domain.enumeration.RoleType;


@Data
public abstract class UtilisateurDtoResponse {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;
    private RoleType role;
    private Boolean actif;
}

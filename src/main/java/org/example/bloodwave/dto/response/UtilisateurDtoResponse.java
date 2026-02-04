package org.example.bloodwave.dto.response;

import lombok.Data;
import org.example.bloodwave.enumeration.RoleType;


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

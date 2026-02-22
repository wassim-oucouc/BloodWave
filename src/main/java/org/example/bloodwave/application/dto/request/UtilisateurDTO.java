package org.example.bloodwave.application.dto.request;



import lombok.Data;
import org.example.bloodwave.domain.enumeration.RoleType;

@Data
public abstract class UtilisateurDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String telephone;
    private String adresse;
    private String ville;
    private RoleType role;
    private Boolean actif;
}

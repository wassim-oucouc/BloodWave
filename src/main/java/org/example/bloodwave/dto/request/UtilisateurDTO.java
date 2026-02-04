package org.example.bloodwave.dto.request;



import lombok.Data;
import org.example.bloodwave.enumeration.RoleType;

@Data
public abstract class UtilisateurDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String telephone;
    private String adresse;
    private RoleType role;
    private Boolean actif;
}

package org.example.bloodwave.application.dto.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import org.example.bloodwave.domain.enumeration.RoleType;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "role", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DonneurDTO.class,   name = "DONNEUR"),
        @JsonSubTypes.Type(value = DemandeurDTO.class, name = "DEMANDEUR"),
        @JsonSubTypes.Type(value = HopitalDTO.class,   name = "HOPITAL"),
        @JsonSubTypes.Type(value = AdminDTO.class,     name = "ADMIN")
})
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

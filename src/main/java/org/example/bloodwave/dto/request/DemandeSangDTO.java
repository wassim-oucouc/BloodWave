package org.example.bloodwave.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.example.bloodwave.entity.Demandeur;
import org.example.bloodwave.entity.Hopital;
import org.example.bloodwave.enumeration.GroupeSanguin;
import org.example.bloodwave.enumeration.NiveauUrgence;
import org.example.bloodwave.enumeration.StatutDemande;

import java.time.LocalDateTime;


@Data
public class DemandeSangDTO {

    private Long id;

    private GroupeSanguin groupeSanguin;

    private Integer quantiteDemandee;

    private NiveauUrgence urgence;

    private StatutDemande statut;

    private LocalDateTime dateCreation;

    private Long demandeurId;

    private Long hopitalId;
}

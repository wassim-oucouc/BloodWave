package org.example.bloodwave.application.dto.request;

import lombok.Data;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.example.bloodwave.domain.enumeration.NiveauUrgence;
import org.example.bloodwave.domain.enumeration.StatutDemande;

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

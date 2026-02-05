package org.example.bloodwave.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.example.bloodwave.domain.enumeration.NiveauUrgence;
import org.example.bloodwave.domain.enumeration.StatutDemande;
import java.time.LocalDateTime;

@Entity
@Data
public class DemandeSang{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private GroupeSanguin groupeSanguin;

    private Integer quantiteDemandee;

    @Enumerated(EnumType.STRING)
    private NiveauUrgence urgence;

    @Enumerated(EnumType.STRING)
    private StatutDemande statut;

    private LocalDateTime dateCreation;

    @ManyToOne
    private Demandeur demandeur;

    @ManyToOne
    private Hopital hopital;
}

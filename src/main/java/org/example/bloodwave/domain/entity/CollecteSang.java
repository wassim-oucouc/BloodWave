package org.example.bloodwave.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.bloodwave.domain.enumeration.StatutCollecte;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class CollecteSang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCollecte;
    private String lieu;
    private Integer capaciteMax;
    private String description;
    @Enumerated(EnumType.STRING)
    private StatutCollecte statut;

    @ManyToOne
    private Hopital hopital;

    @OneToMany(mappedBy = "collecte")
    private List<InscriptionCollecte> inscriptions;
}

package org.example.bloodwave.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.bloodwave.domain.enumeration.StatutCollecte;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class CollecteSang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateCollecte;
    private String lieu;
    private Integer capaciteMax;

    @Enumerated(EnumType.STRING)
    private StatutCollecte statut;

    @ManyToOne
    private Hopital hopital;

    @OneToMany(mappedBy = "collecte")
    private List<InscriptionCollecte> inscriptions;
}

package org.example.bloodwave.domain.entity;

import jakarta.persistence.*;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;

import java.util.List;

@Entity
public class StockSang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private GroupeSanguin groupeSanguin;

    private Integer quantiteDisponible;
    private Integer seuilAlerte;

    @ManyToOne
    private Hopital hopital;

    @OneToMany(mappedBy = "stockSang")
    private List<UniteSang> unites;

    @OneToMany(mappedBy = "stockSang")
    private List<MouvementStock> mouvements;
}

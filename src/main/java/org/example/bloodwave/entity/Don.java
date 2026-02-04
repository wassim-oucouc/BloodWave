package org.example.bloodwave.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.example.bloodwave.enumeration.StatutDon;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Don {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate datePrevue;
    private LocalDateTime dateEffective;
    private Double quantite;

    @Enumerated(EnumType.STRING)
    private StatutDon statut;

    @ManyToOne
    private Donneur donneur;

    @ManyToOne
    private Hopital hopital;

    @OneToMany(mappedBy = "don", cascade = CascadeType.ALL)
    private List<UniteSang> unites;
}
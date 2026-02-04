package org.example.bloodwave.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.bloodwave.enumeration.StatutUnite;

import java.time.LocalDate;

@Entity
@Data
public class UniteSang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroUnite;
    private Double volume;
    private LocalDate datePrelevement;
    private LocalDate dateExpiration;

    @Enumerated(EnumType.STRING)
    private StatutUnite statut;

    @ManyToOne
    private Don don;

    @ManyToOne
    private StockSang stockSang;


}

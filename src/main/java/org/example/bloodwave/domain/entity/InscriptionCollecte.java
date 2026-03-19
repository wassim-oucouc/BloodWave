package org.example.bloodwave.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class InscriptionCollecte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Donneur donneur;

    private boolean joined;

    @ManyToOne
    private CollecteSang collecte;
}

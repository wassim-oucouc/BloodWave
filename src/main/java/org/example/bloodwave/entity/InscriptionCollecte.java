package org.example.bloodwave.entity;

import jakarta.persistence.*;

@Entity
public class InscriptionCollecte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Donneur donneur;

    @ManyToOne
    private CollecteSang collecte;
}

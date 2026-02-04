package org.example.bloodwave.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Hopital extends Utilisateur {

    private String nomHopital;
    private String ville;
    private Integer capaciteStockage;

    @OneToMany(mappedBy = "hopital")
    private List<StockSang> stocks;

    @OneToMany(mappedBy = "hopital")
    private List<Don> donsRecus;

    @OneToMany(mappedBy = "hopital")
    private List<CollecteSang> collectes;
}
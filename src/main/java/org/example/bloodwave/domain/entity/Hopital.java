package org.example.bloodwave.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Hopital extends Utilisateur {

    private String nomHopital;
    private Integer capaciteStockage;

    @OneToMany(mappedBy = "hopital")
    private List<StockSang> stocks;

    @OneToMany(mappedBy = "hopital")
    private List<Don> donsRecus;

    @OneToMany(mappedBy = "hopital")
    private List<CollecteSang> collectes;
}
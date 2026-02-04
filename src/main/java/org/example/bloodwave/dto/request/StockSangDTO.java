package org.example.bloodwave.dto.request;

import jakarta.persistence.*;
import org.example.bloodwave.entity.Hopital;
import org.example.bloodwave.entity.MouvementStock;
import org.example.bloodwave.entity.UniteSang;
import org.example.bloodwave.enumeration.GroupeSanguin;

import java.util.List;

public class StockSangDTO {


    private Long id;

    private GroupeSanguin groupeSanguin;

    private Integer quantiteDisponible;
    private Integer seuilAlerte;

    private Long hopitalId;

    private List<UniteSang> unites;

    private List<MouvementStock> mouvements;
}

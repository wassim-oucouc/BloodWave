package org.example.bloodwave.application.dto.request;

import org.example.bloodwave.domain.entity.MouvementStock;
import org.example.bloodwave.domain.entity.UniteSang;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;

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

package org.example.bloodwave.dto.response;


import lombok.Data;
import org.example.bloodwave.entity.MouvementStock;
import org.example.bloodwave.entity.UniteSang;
import org.example.bloodwave.enumeration.GroupeSanguin;

import java.util.List;

@Data
public class StockSangDtoResponse {

    private Long id;

    private GroupeSanguin groupeSanguin;

    private Integer quantiteDisponible;
    private Integer seuilAlerte;

    private HopitalDtoResponse hopitalDtoResponse;

    private List<UniteSangDtoResponse> uniteSangDtoResponses;

    private List<MouvementStockDtoResponse> mouvements;
}

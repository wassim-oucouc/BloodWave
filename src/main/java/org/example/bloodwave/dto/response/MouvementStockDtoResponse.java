package org.example.bloodwave.dto.response;

import org.example.bloodwave.entity.StockSang;
import org.example.bloodwave.enumeration.TypeMouvement;

import java.time.LocalDateTime;

public class MouvementStockDtoResponse {

    private Long id;

    private TypeMouvement type;

    private Integer quantite;

    private LocalDateTime date;

    private StockSangDtoResponse stockSangDtoResponse;
}

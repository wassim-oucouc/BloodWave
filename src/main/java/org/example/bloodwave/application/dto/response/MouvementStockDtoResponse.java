package org.example.bloodwave.application.dto.response;

import org.example.bloodwave.domain.enumeration.TypeMouvement;

import java.time.LocalDateTime;

public class MouvementStockDtoResponse {

    private Long id;

    private TypeMouvement type;

    private Integer quantite;

    private LocalDateTime date;

    private StockSangDtoResponse stockSangDtoResponse;
}

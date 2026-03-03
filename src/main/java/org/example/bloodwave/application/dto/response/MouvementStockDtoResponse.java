package org.example.bloodwave.application.dto.response;

import lombok.Data;
import org.example.bloodwave.domain.enumeration.TypeMouvement;

import java.time.LocalDateTime;

@Data
public class MouvementStockDtoResponse {

    private Long id;

    private TypeMouvement type;

    private Integer quantite;

    private LocalDateTime date;

    private StockSangDtoResponse stockSangDtoResponse;

    private UniteSangDtoResponse uniteSangDtoResponse;
}

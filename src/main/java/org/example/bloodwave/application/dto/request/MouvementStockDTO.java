package org.example.bloodwave.application.dto.request;

import lombok.Data;
import org.example.bloodwave.domain.entity.StockSang;
import org.example.bloodwave.domain.enumeration.TypeMouvement;

import java.time.LocalDateTime;

@Data
public class MouvementStockDTO {

    private Long id;

    private TypeMouvement type;

    private Integer quantite;
    private LocalDateTime date;

    private Long stockSangId;
}

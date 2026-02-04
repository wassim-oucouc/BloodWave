package org.example.bloodwave.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.example.bloodwave.entity.StockSang;
import org.example.bloodwave.enumeration.TypeMouvement;

import java.time.LocalDateTime;

@Data
public class MouvementStockDTO {

    private Long id;

    private TypeMouvement type;

    private Integer quantite;
    private LocalDateTime date;

    private StockSang stockSang;
}

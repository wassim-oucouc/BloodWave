package org.example.bloodwave.application.dto.request;



import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.example.bloodwave.domain.entity.Don;

import java.util.List;

@Setter
@Getter
public class HopitalDTO extends UtilisateurDTO {

    private String nomHopital;
    private Integer capaciteStockage;

    private List<StockSangDTO> stockSangDTOS;

    private List<Don> dons;

    private List<CollecteSangDTO> collecteSangDTOS;
}

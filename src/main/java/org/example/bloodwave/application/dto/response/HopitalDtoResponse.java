package org.example.bloodwave.application.dto.response;

import lombok.Data;
import org.example.bloodwave.application.dto.request.CollecteSangDTO;
import org.example.bloodwave.application.dto.request.StockSangDTO;
import org.example.bloodwave.domain.entity.Don;

import java.util.List;

@Data
public class HopitalDtoResponse extends UtilisateurDtoResponse{

    private String nomHopital;
    private String imageHopital;
    private Integer capaciteStockage;

    private List<StockSangDTO> stockSangDTOS;

    private List<Don> dons;

    private List<CollecteSangDTO> collecteSangDTOS;
}

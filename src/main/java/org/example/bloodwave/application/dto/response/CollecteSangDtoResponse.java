package org.example.bloodwave.application.dto.response;

import lombok.Data;
import org.example.bloodwave.domain.enumeration.StatutCollecte;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CollecteSangDtoResponse {

    private Long id;
    private LocalDateTime dateCollecte;
    private String lieu;
    private Integer capaciteMax;
    private String description;
    private StatutCollecte statut;

    private HopitalDtoResponse hopitalDtoResponse;

    private List<InscriptionCollecteDtoResponse> inscriptionCollecteDtoResponses;
}

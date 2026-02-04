package org.example.bloodwave.dto.response;

import lombok.Data;
import org.example.bloodwave.enumeration.StatutCollecte;

import java.time.LocalDate;
import java.util.List;

@Data
public class CollecteSangDtoResponse {

    private Long id;

    private LocalDate dateCollecte;
    private String lieu;
    private Integer capaciteMax;

    private StatutCollecte statut;

    private HopitalDtoResponse hopitalDtoResponse;

    private List<InscriptionCollecteDtoResponse> inscriptionCollecteDtoResponses;
}

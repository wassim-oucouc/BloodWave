package org.example.bloodwave.application.dto.response;


import lombok.Data;
import org.example.bloodwave.domain.enumeration.StatutDon;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class DonDtoResponse {

    private Long id;

    private LocalDate datePrevue;
    private LocalDateTime dateEffective;
    private Double quantite;

    private StatutDon statut;

    private DonneurDtoResponse donneurDtoResponse;

    private HopitalDtoResponse hopitalDtoResponse;

    private List<UniteSangDtoResponse> uniteSangDtoResponses;
}

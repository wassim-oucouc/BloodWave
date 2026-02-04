package org.example.bloodwave.dto.response;


import lombok.Data;
import org.example.bloodwave.dto.request.UniteSangDTO;
import org.example.bloodwave.enumeration.StatutDon;

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

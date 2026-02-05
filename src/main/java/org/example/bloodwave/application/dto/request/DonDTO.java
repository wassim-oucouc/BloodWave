package org.example.bloodwave.application.dto.request;

import lombok.Data;
import org.example.bloodwave.domain.enumeration.StatutDon;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class DonDTO {

    private Long id;

    private LocalDate datePrevue;
    private LocalDateTime dateEffective;
    private Double quantite;

    private StatutDon statut;

    private Long donneurId;

    private Long hopitalId;

    private List<UniteSangDTO> uniteSangDTOS;
}

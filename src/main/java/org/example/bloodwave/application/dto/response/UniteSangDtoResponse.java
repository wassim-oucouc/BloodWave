package org.example.bloodwave.application.dto.response;

import lombok.Data;
import org.example.bloodwave.domain.entity.Don;
import org.example.bloodwave.domain.enumeration.StatutUnite;

import java.time.LocalDate;


@Data
public class UniteSangDtoResponse {
    private Long id;

    private String numeroUnite;
    private Double volume;
    private LocalDate datePrelevement;
    private LocalDate dateExpiration;

    private StatutUnite statut;

    private Don don;
    private StockSangDtoResponse stockSangDtoResponse;
}

package org.example.bloodwave.application.dto.request;

import lombok.Data;
import org.example.bloodwave.domain.entity.StockSang;
import org.example.bloodwave.domain.enumeration.StatutUnite;

import java.time.LocalDate;

@Data
public class UniteSangDTO {

    private Long id;
    private String numeroUnite;
    private Double volume;
    private LocalDate datePrelevement;
    private LocalDate dateExpiration;
    private StatutUnite statut;
    private Long stockSangId;
    private Long donId;

}

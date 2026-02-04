package org.example.bloodwave.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.example.bloodwave.entity.Don;
import org.example.bloodwave.entity.StockSang;
import org.example.bloodwave.enumeration.StatutUnite;

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

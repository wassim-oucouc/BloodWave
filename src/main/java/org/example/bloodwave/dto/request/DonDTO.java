package org.example.bloodwave.dto.request;

import jakarta.persistence.*;
import lombok.Data;
import org.example.bloodwave.entity.Donneur;
import org.example.bloodwave.entity.Hopital;
import org.example.bloodwave.entity.UniteSang;
import org.example.bloodwave.enumeration.StatutDon;

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

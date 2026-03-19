package org.example.bloodwave.application.dto.request;

import lombok.Data;
import org.example.bloodwave.domain.entity.InscriptionCollecte;
import org.example.bloodwave.domain.enumeration.StatutCollecte;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data
public class CollecteSangDTO {

    private Long id;

    private LocalDateTime dateCollecte;
    private String lieu;
    private Integer capaciteMax;
    private String description;

    private StatutCollecte statut;

    private Long hopitalId;

    private List<InscriptionCollecte> inscriptions;
}

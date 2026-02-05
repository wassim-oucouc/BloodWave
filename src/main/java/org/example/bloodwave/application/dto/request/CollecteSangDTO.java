package org.example.bloodwave.application.dto.request;

import lombok.Data;
import org.example.bloodwave.domain.entity.InscriptionCollecte;
import org.example.bloodwave.domain.enumeration.StatutCollecte;

import java.time.LocalDate;
import java.util.List;


@Data
public class CollecteSangDTO {

    private Long id;

    private LocalDate dateCollecte;
    private String lieu;
    private Integer capaciteMax;

    private StatutCollecte statut;

    private Long hopitalId;

    private List<InscriptionCollecte> inscriptions;
}

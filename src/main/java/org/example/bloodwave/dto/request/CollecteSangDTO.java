package org.example.bloodwave.dto.request;

import jakarta.persistence.*;
import lombok.Data;
import org.example.bloodwave.entity.Hopital;
import org.example.bloodwave.entity.InscriptionCollecte;
import org.example.bloodwave.enumeration.StatutCollecte;

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

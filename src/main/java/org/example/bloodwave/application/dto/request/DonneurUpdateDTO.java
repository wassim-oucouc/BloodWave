package org.example.bloodwave.application.dto.request;

import lombok.Data;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;

import java.time.LocalDate;

@Data
public class DonneurUpdateDTO {
    private Double poids;
    private GroupeSanguin groupeSanguin;
    private LocalDate dateNaissance;
    private LocalDate dateDernierDon;
    private Boolean disponible;
    private Boolean aMaladieChronique;
    private Boolean estSousTraitement;
    private Boolean aSubiChirurgieRecente;
    private Boolean estEnceinte;
    private Boolean aInfectionRecente;
}

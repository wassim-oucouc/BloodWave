package org.example.bloodwave.application.dto.response;

import org.example.bloodwave.application.dto.request.DonDTO;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;

import java.time.LocalDate;
import java.util.List;

public class DonneurDtoResponse extends UtilisateurDtoResponse{

    private GroupeSanguin groupeSanguin;
    private LocalDate dateDernierDon;
    private Boolean disponible;
    private Integer nombreDonsTotaux;
    private LocalDate dateNaissance;
    private Double poids;
    private Boolean aMaladieChronique;
    private Boolean estSousTraitement;
    private Boolean aSubiChirurgieRecente;
    private Boolean estEnceinte;
    private Boolean aInfectionRecente;
    private List<DonDtoResponse> dtoResponses;
}

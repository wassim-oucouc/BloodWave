package org.example.bloodwave.application.dto.request;


import lombok.Getter;
import lombok.Setter;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
public class DonneurDTO extends UtilisateurDTO{


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
    private List<DonDTO> dtos;

    private List<InscriptionCollecteDTO> inscriptionCollecteDTOS;
}

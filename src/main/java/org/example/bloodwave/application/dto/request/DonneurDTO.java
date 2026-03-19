package org.example.bloodwave.application.dto.request;


import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
public class DonneurDTO extends UtilisateurDTO{


    private GroupeSanguin groupeSanguin;

    @JsonAlias({"lastDonationDate", "dateDernierDon"})
    private LocalDate dateDernierDon;

    private Boolean disponible;
    private Integer nombreDonsTotaux;

    @JsonAlias({"dateOfBirth", "getDateOfBirth", "date_naissance"})
    private LocalDate dateNaissance;

    @JsonAlias({"weight", "poids"})
    private Double poids;

    private Boolean aMaladieChronique;
    private Boolean estSousTraitement;
    private Boolean aSubiChirurgieRecente;
    private Boolean estEnceinte;
    private Boolean aInfectionRecente;
    private List<DonDTO> dtos;

    private List<InscriptionCollecteDTO> inscriptionCollecteDTOS;
}

package org.example.bloodwave.dto.request;


import lombok.Getter;
import lombok.Setter;
import org.example.bloodwave.enumeration.GroupeSanguin;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
public class DonneurDTO extends UtilisateurDTO{


    private GroupeSanguin groupeSanguin;
    private LocalDate dateDernierDon;
    private Boolean disponible;
    private Integer nombreDonsTotaux;
    private List<Long> donIds;

    private List<InscriptionCollecteDTO> inscriptionCollecteDTOS;
}

package org.example.bloodwave.application.dto.request;

import lombok.Data;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;

import java.util.List;

@Data
public class DemandeurDTO extends UtilisateurDTO{
    GroupeSanguin groupeSanguin;
    private List<DemandeSangDTO> demandeSangDTOS;




}

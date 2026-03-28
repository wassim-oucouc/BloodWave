package org.example.bloodwave.application.dto.response;

import lombok.Data;
import org.example.bloodwave.application.dto.request.DemandeSangDTO;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;

import java.util.List;

@Data
public class DemandeurDtoResponse extends UtilisateurDtoResponse{

    GroupeSanguin groupeSanguin;
    private List<DemandeurDtoResponse> demandeurDtoResponses;
}

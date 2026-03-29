package org.example.bloodwave.application.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;

import java.util.List;

@Data
public class DemandeurDTO extends UtilisateurDTO{
    @Enumerated(EnumType.STRING)
    GroupeSanguin groupeSanguin;
    private List<DemandeSangDTO> demandeSangDTOS;




}

package org.example.bloodwave.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class DemandeurDTO extends UtilisateurDTO{
    private List<Long> demandeSangIds;




}

package org.example.bloodwave.application.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class DemandeurDTO extends UtilisateurDTO{
    private List<DemandeSangDTO> demandeSangDTOS;




}

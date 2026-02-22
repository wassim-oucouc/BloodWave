package org.example.bloodwave.application.service;

import org.example.bloodwave.application.dto.request.DemandeurDTO;
import org.example.bloodwave.application.dto.response.DemandeurDtoResponse;
import org.springframework.stereotype.Service;

@Service
public interface DemandeurService {
    public DemandeurDtoResponse registerDemandeur(DemandeurDTO dto);

}

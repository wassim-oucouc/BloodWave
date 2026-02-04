package org.example.bloodwave.service;

import org.example.bloodwave.dto.request.DemandeurDTO;
import org.example.bloodwave.dto.response.DemandeurDtoResponse;
import org.springframework.stereotype.Service;

@Service
public interface DemandeurService {
    public DemandeurDtoResponse registerDemandeur(DemandeurDTO dto);

}

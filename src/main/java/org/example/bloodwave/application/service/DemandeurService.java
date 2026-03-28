package org.example.bloodwave.application.service;

import org.example.bloodwave.application.dto.request.DemandeurDTO;
import org.example.bloodwave.application.dto.response.DemandeSangDtoResponse;
import org.example.bloodwave.application.dto.response.DemandeurDtoResponse;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DemandeurService {
    DemandeurDtoResponse registerDemandeur(DemandeurDTO dto);
    
    DemandeurDtoResponse getDemandeurById(Long id);
    
    List<DemandeurDtoResponse> getAllDemandeurs();
    
    DemandeurDtoResponse updateDemandeur(Long id, DemandeurDTO dto);
    
    void deleteDemandeur(Long id);
    
    List<DemandeSangDtoResponse> getDemandesByDemandeurId(Long demandeurId);
    
    List<DemandeurDtoResponse> getDemandeursByGroupeSanguin(GroupeSanguin groupeSanguin);
}

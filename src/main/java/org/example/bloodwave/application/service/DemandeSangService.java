package org.example.bloodwave.application.service;


import org.example.bloodwave.application.dto.request.DemandeSangDTO;
import org.example.bloodwave.application.dto.response.DemandeSangDtoResponse;
import org.example.bloodwave.domain.enumeration.StatutDemande;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DemandeSangService {

    DemandeSangDtoResponse create(DemandeSangDTO dto);

    List<DemandeSangDtoResponse> getByDemandeur(Long demandeurId);

     List<DemandeSangDtoResponse> getAll();

        DemandeSangDtoResponse getById(Long id);

    DemandeSangDtoResponse updateStatut(Long id, StatutDemande statut);

    List<DemandeSangDtoResponse> getDemandesByHopital(Long hopitalId);

    DemandeSangDtoResponse traiterDemande(Long demandeId, StatutDemande statut);
}
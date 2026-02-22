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

    public List<DemandeSangDtoResponse> getAll();

        DemandeSangDtoResponse getById(Long id);

    DemandeSangDtoResponse updateStatut(Long id, StatutDemande statut);

    public void approveDemande(Long demandeId);

    public void rejectDemande(Long demandeId);



}
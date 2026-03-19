package org.example.bloodwave.application.service;


import org.example.bloodwave.application.dto.request.CollecteSangDTO;
import org.example.bloodwave.application.dto.response.CollecteSangDtoResponse;
import org.example.bloodwave.application.exceptions.CollecteNotFoundException;
import org.example.bloodwave.application.exceptions.HopitalNotFoundException;
import org.example.bloodwave.domain.entity.CollecteSang;
import org.example.bloodwave.domain.entity.Donneur;
import org.example.bloodwave.domain.entity.Hopital;
import org.example.bloodwave.domain.entity.InscriptionCollecte;
import org.example.bloodwave.domain.enumeration.StatutCollecte;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CollecteSangService {

    public CollecteSangDtoResponse createCollecte(CollecteSangDTO dto);
    public CollecteSangDtoResponse updateCollecte(Long id, CollecteSangDTO dto);
    public void deleteCollecte(Long id);
    public List<CollecteSangDtoResponse> getAllCollectes();
    public CollecteSangDtoResponse getCollecteById(Long id);
    public CollecteSangDtoResponse joinCollecte(Long collecteId, Donneur donneur);
    public CollecteSangDtoResponse cancelParticipation(Long collecteId, Donneur donneur);
    public List<CollecteSangDtoResponse> getCollectesByStatus(StatutCollecte statut);
    public List<CollecteSangDtoResponse> getCollectsByHopitalId(Long hopitalId);
}

package org.example.bloodwave.application.service.impl;

import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.response.InscriptionCollecteDtoResponse;
import org.example.bloodwave.application.exceptions.DonneurNotFoundException;
import org.example.bloodwave.application.exceptions.InscriptionCollecteNotFoundException;
import org.example.bloodwave.application.mapper.CollecteSangMapper;
import org.example.bloodwave.application.mapper.DonneurMapper;
import org.example.bloodwave.application.service.InscriptionCollecteService;
import org.example.bloodwave.domain.entity.CollecteSang;
import org.example.bloodwave.domain.entity.Donneur;
import org.example.bloodwave.domain.entity.InscriptionCollecte;
import org.example.bloodwave.domain.repository.DonneurRepository;
import org.example.bloodwave.domain.repository.InscriptionCollecteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class InscriptionCollecteServiceImpl implements InscriptionCollecteService {

    private final InscriptionCollecteRepository inscriptionCollecteRepository;
    private final DonneurRepository donneurRepository;
    private final DonneurMapper donneurMapper;
    private final CollecteSangMapper collecteSangMapper;

    @Override
    @Transactional(readOnly = true)
    public List<InscriptionCollecteDtoResponse> getInscriptionsByDonneurId(Long donneurId) {
        if (!donneurRepository.existsById(donneurId)) {
            throw new DonneurNotFoundException("Donneur not found with id " + donneurId);
        }

        Donneur fallbackDonneur = donneurRepository.findById(donneurId)
                .orElseThrow(() -> new DonneurNotFoundException("Donneur not found with id " + donneurId));

        return inscriptionCollecteRepository.findAllByDonneurIdWithDetails(donneurId)
                .stream()
                .map(inscription -> {
                    InscriptionCollecteDtoResponse dto = new InscriptionCollecteDtoResponse();
                    dto.setId(inscription.getId());
                    dto.setJoined(inscription.isJoined());

                    Donneur donneur = inscription.getDonneur();
                    if (donneur == null) {
                        donneur = inscriptionCollecteRepository.findDonneurByInscriptionId(inscription.getId())
                                .orElse(fallbackDonneur);
                    }
                    dto.setDonneurDtoResponse(donneurMapper.toDtoResponse(donneur));

                    CollecteSang collecte = inscription.getCollecte();
                    if (collecte == null) {
                        collecte = inscriptionCollecteRepository.findCollecteByInscriptionId(inscription.getId()).orElse(null);
                    }
                    if (collecte != null) {
                        dto.setCollecteSangDtoResponse(collecteSangMapper.toDtoResponse(collecte));
                    }

                    return dto;
                })
                .toList();
    }

    @Override
    public void deleteInscriptionById(Long inscriptionId) {
        InscriptionCollecte inscription = inscriptionCollecteRepository.findById(inscriptionId)
                .orElseThrow(() -> new InscriptionCollecteNotFoundException("Inscription not found with id " + inscriptionId));

        inscriptionCollecteRepository.delete(inscription);
    }
}

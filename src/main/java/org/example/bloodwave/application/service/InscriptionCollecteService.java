package org.example.bloodwave.application.service;

import org.example.bloodwave.application.dto.response.InscriptionCollecteDtoResponse;

import java.util.List;

public interface InscriptionCollecteService {

    List<InscriptionCollecteDtoResponse> getInscriptionsByDonneurId(Long donneurId);

    void deleteInscriptionById(Long inscriptionId);
}

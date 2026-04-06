package org.example.bloodwave.application.service;

import org.example.bloodwave.application.dto.response.InscriptionCollecteDtoResponse;

import java.util.List;

public interface InscriptionCollecteService {

    List<InscriptionCollecteDtoResponse> getInscriptionsByDonneurId(Long donneurId);

    List<InscriptionCollecteDtoResponse> getInscriptionsByCollectId(Long collectId);

    void deleteInscriptionById(Long inscriptionId);

    void deleteInscriptionByCollectAndDonneur(Long collectId, Long donneurId);
}

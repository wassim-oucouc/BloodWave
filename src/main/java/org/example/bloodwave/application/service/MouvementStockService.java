package org.example.bloodwave.application.service;

import org.example.bloodwave.application.dto.request.MouvementStockDTO;
import org.example.bloodwave.application.dto.response.MouvementStockDtoResponse;
import org.example.bloodwave.domain.enumeration.TypeMouvement;

import java.util.List;

public interface MouvementStockService {

    MouvementStockDtoResponse createMouvement(MouvementStockDTO dto);

    MouvementStockDtoResponse updateMouvement(Long id, MouvementStockDTO dto);

    void deleteMouvement(Long id);

    MouvementStockDtoResponse getMouvementById(Long id);

    List<MouvementStockDtoResponse> getAllMouvements();

    List<MouvementStockDtoResponse> getMouvementsByStockId(Long stockId);

    List<MouvementStockDtoResponse> getMouvementsByType(TypeMouvement type);

    List<MouvementStockDtoResponse> getMouvementsByUniteId(Long uniteId);

    List<MouvementStockDtoResponse> getMouvementsByHopitalId(Long hopitalId);
}

package org.example.bloodwave.application.service.impl;

import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.request.MouvementStockDTO;
import org.example.bloodwave.application.dto.response.MouvementStockDtoResponse;
import org.example.bloodwave.application.exceptions.StockSangNotFoundException;
import org.example.bloodwave.application.exceptions.UnitSangNotFoundException;
import org.example.bloodwave.application.mapper.MouvementStockMapper;
import org.example.bloodwave.application.service.MouvementStockService;
import org.example.bloodwave.domain.entity.MouvementStock;
import org.example.bloodwave.domain.entity.StockSang;
import org.example.bloodwave.domain.entity.UniteSang;
import org.example.bloodwave.domain.enumeration.TypeMouvement;
import org.example.bloodwave.domain.repository.MouvementStockRepository;
import org.example.bloodwave.domain.repository.StockSangRepository;
import org.example.bloodwave.domain.repository.UnitSangRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MouvementStockServiceImpl implements MouvementStockService {

    private final MouvementStockRepository mouvementStockRepository;
    private final StockSangRepository stockSangRepository;
    private final UnitSangRepository unitSangRepository;
    private final MouvementStockMapper mouvementStockMapper;

    @Override
    public MouvementStockDtoResponse createMouvement(MouvementStockDTO dto) {
        StockSang stock = stockSangRepository.findById(dto.getStockSangId())
                .orElseThrow(() -> new StockSangNotFoundException("Stock not found with id " + dto.getStockSangId()));

        UniteSang unite = unitSangRepository.findById(dto.getUniteSangId())
                .orElseThrow(() -> new UnitSangNotFoundException("Unite not found with id " + dto.getUniteSangId()));

        MouvementStock mouvement = mouvementStockMapper.toEntity(dto);
        mouvement.setStockSang(stock);
        mouvement.setUniteSang(unite);
        
        if (mouvement.getDate() == null) {
            mouvement.setDate(LocalDateTime.now());
        }

        MouvementStock saved = mouvementStockRepository.save(mouvement);
        return mouvementStockMapper.toDtoResponse(saved);
    }

    @Override
    public MouvementStockDtoResponse updateMouvement(Long id, MouvementStockDTO dto) {
        MouvementStock mouvement = mouvementStockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mouvement not found with id " + id));

        if (dto.getType() != null) {
            mouvement.setType(dto.getType());
        }
        if (dto.getQuantite() != null) {
            mouvement.setQuantite(dto.getQuantite());
        }
        if (dto.getDate() != null) {
            mouvement.setDate(dto.getDate());
        }
        if (dto.getStockSangId() != null) {
            StockSang stock = stockSangRepository.findById(dto.getStockSangId())
                    .orElseThrow(() -> new StockSangNotFoundException("Stock not found with id " + dto.getStockSangId()));
            mouvement.setStockSang(stock);
        }
        if (dto.getUniteSangId() != 0) {
            UniteSang unite = unitSangRepository.findById(dto.getUniteSangId())
                    .orElseThrow(() -> new UnitSangNotFoundException("Unite not found with id " + dto.getUniteSangId()));
            mouvement.setUniteSang(unite);
        }

        MouvementStock updated = mouvementStockRepository.save(mouvement);
        return mouvementStockMapper.toDtoResponse(updated);
    }

    @Override
    public void deleteMouvement(Long id) {
        MouvementStock mouvement = mouvementStockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mouvement not found with id " + id));
        mouvementStockRepository.delete(mouvement);
    }

    @Override
    public MouvementStockDtoResponse getMouvementById(Long id) {
        MouvementStock mouvement = mouvementStockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mouvement not found with id " + id));
        return mouvementStockMapper.toDtoResponse(mouvement);
    }

    @Override
    public List<MouvementStockDtoResponse> getAllMouvements() {
        return mouvementStockRepository.findAll()
                .stream()
                .map(mouvementStockMapper::toDtoResponse)
                .toList();
    }

    @Override
    public List<MouvementStockDtoResponse> getMouvementsByStockId(Long stockId) {
        stockSangRepository.findById(stockId)
                .orElseThrow(() -> new StockSangNotFoundException("Stock not found with id " + stockId));

        return mouvementStockRepository.findAll()
                .stream()
                .filter(m -> m.getStockSang() != null && m.getStockSang().getId().equals(stockId))
                .map(mouvementStockMapper::toDtoResponse)
                .toList();
    }

    @Override
    public List<MouvementStockDtoResponse> getMouvementsByType(TypeMouvement type) {
        return mouvementStockRepository.findAll()
                .stream()
                .filter(m -> m.getType() == type)
                .map(mouvementStockMapper::toDtoResponse)
                .toList();
    }

    @Override
    public List<MouvementStockDtoResponse> getMouvementsByUniteId(Long uniteId) {
        unitSangRepository.findById(uniteId)
                .orElseThrow(() -> new UnitSangNotFoundException("Unite not found with id " + uniteId));

        return mouvementStockRepository.findAll()
                .stream()
                .filter(m -> m.getUniteSang() != null && m.getUniteSang().getId().equals(uniteId))
                .map(mouvementStockMapper::toDtoResponse)
                .toList();
    }

    @Override
    public List<MouvementStockDtoResponse> getMouvementsByHopitalId(Long hopitalId) {
        return mouvementStockRepository.findByHopitalId(hopitalId)
                .stream()
                .map(mouvementStockMapper::toDtoResponse)
                .toList();
    }
}

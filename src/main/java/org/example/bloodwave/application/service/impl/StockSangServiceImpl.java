package org.example.bloodwave.application.service.impl;

import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.request.StockSangDTO;
import org.example.bloodwave.application.dto.response.StockSangDtoResponse;
import org.example.bloodwave.application.mapper.StockSangMapper;
import org.example.bloodwave.application.service.StockSangService;
import org.example.bloodwave.domain.entity.Hopital;
import org.example.bloodwave.domain.entity.StockSang;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.example.bloodwave.domain.repository.HopitalRepository;
import org.example.bloodwave.domain.repository.StockSangRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StockSangServiceImpl implements StockSangService {

    private final StockSangRepository stockSangRepository;
    private final HopitalRepository hopitalRepository;
    private final StockSangMapper stockSangMapper;

    @Override
    public StockSangDtoResponse addOrUpdateStock(StockSangDTO dto) {
        Hopital hopital = hopitalRepository.findById(dto.getHopitalId())
                .orElseThrow(() -> new RuntimeException("Hopital non trouvé"));

        StockSang stock = stockSangRepository
                .findByHopitalIdAndGroupeSanguin(hopital.getId(), dto.getGroupeSanguin())
                .orElse(new StockSang());

        stock.setHopital(hopital);
        stock.setGroupeSanguin(dto.getGroupeSanguin());
        stock.setQuantiteDisponible(dto.getQuantiteDisponible());

        return stockSangMapper.toDtoResponse(stockSangRepository.save(stock));
    }

    @Override
    public List<StockSangDtoResponse> getStockByHopital(Long hopitalId) {
        return stockSangRepository.findByHopitalId(hopitalId)
                .stream()
                .map(stockSangMapper::toDtoResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StockSangDtoResponse getStockByHopitalAndGroupe(Long hopitalId, GroupeSanguin groupe) {
        StockSang stock = stockSangRepository
                .findByHopitalIdAndGroupeSanguin(hopitalId, groupe)
                .orElseThrow(() -> new RuntimeException("Stock non trouvé"));

        return stockSangMapper.toDtoResponse(stock);
    }
}

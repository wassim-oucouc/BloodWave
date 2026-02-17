package org.example.bloodwave.application.service;

import org.example.bloodwave.application.dto.request.StockSangDTO;
import org.example.bloodwave.application.dto.response.StockSangDtoResponse;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;

import java.util.List;

public interface StockSangService {

    StockSangDtoResponse addOrUpdateStock(StockSangDTO dto);

    List<StockSangDtoResponse> getStockByHopital(Long hopitalId);

    StockSangDtoResponse getStockByHopitalAndGroupe(Long hopitalId, GroupeSanguin groupe);
}

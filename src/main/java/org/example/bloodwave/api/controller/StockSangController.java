package org.example.bloodwave.api.controller;


import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.request.StockSangDTO;
import org.example.bloodwave.application.dto.response.StockSangDtoResponse;
import org.example.bloodwave.application.service.StockSangService;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@AllArgsConstructor
public class StockSangController {

    private final StockSangService stockSangService;

    @PostMapping
    public ResponseEntity<StockSangDtoResponse> addOrUpdateStock(@RequestBody StockSangDTO dto) {
        return ResponseEntity.ok(stockSangService.addOrUpdateStock(dto));
    }

    @GetMapping("/hopital/{hopitalId}")
    public ResponseEntity<List<StockSangDtoResponse>> getStockByHopital(@PathVariable Long hopitalId) {
        return ResponseEntity.ok(stockSangService.getStockByHopital(hopitalId));
    }

    @GetMapping("/hopital/{hopitalId}/groupe/{groupe}")
    public ResponseEntity<StockSangDtoResponse> getStockByHopitalAndGroupe(
            @PathVariable Long hopitalId,
            @PathVariable GroupeSanguin groupe) {
        return ResponseEntity.ok(stockSangService.getStockByHopitalAndGroupe(hopitalId, groupe));
    }
}

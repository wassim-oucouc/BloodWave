package org.example.bloodwave.api.controller;

import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.request.StockSangDTO;
import org.example.bloodwave.application.dto.response.StockSangDtoResponse;
import org.example.bloodwave.application.dto.response.UniteSangDtoResponse;
import org.example.bloodwave.application.service.StockSangService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@AllArgsConstructor
public class StockSangController {

    private final StockSangService stockSangService;

    @PostMapping
    public ResponseEntity<StockSangDtoResponse> createStock(@RequestBody StockSangDTO dto) {
        return ResponseEntity.ok(stockSangService.createStock(dto));
    }

    @GetMapping
    public ResponseEntity<List<StockSangDtoResponse>> getAllStocks() {
        return ResponseEntity.ok(stockSangService.getAllStocksDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockSangDtoResponse> getStockById(@PathVariable Long id) {
        return ResponseEntity.ok(stockSangService.getStockById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockSangDtoResponse> updateStock(@PathVariable Long id, @RequestBody StockSangDTO dto) {
        return ResponseEntity.ok(stockSangService.updateStock(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockSangService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/unites")
    public ResponseEntity<List<UniteSangDtoResponse>> getStockUnites(@PathVariable Long id) {
        return ResponseEntity.ok(stockSangService.getUnitesByStockId(id));
    }

    @GetMapping("/hopital/{hopitalId}")
    public ResponseEntity<List<StockSangDtoResponse>> getStocksByHopital(@PathVariable Long hopitalId) {
        return ResponseEntity.ok(stockSangService.getStocksByHopitalId(hopitalId));
    }
}

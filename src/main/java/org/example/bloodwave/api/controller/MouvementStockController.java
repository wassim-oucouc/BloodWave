package org.example.bloodwave.api.controller;

import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.request.MouvementStockDTO;
import org.example.bloodwave.application.dto.response.MouvementStockDtoResponse;
import org.example.bloodwave.application.service.MouvementStockService;
import org.example.bloodwave.domain.enumeration.TypeMouvement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mouvements-stock")
@AllArgsConstructor
public class MouvementStockController {

    private final MouvementStockService mouvementStockService;

    @PostMapping
    public ResponseEntity<MouvementStockDtoResponse> createMouvement(@RequestBody MouvementStockDTO dto) {
        return ResponseEntity.ok(mouvementStockService.createMouvement(dto));
    }

    @GetMapping
    public ResponseEntity<List<MouvementStockDtoResponse>> getAllMouvements() {
        return ResponseEntity.ok(mouvementStockService.getAllMouvements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MouvementStockDtoResponse> getMouvementById(@PathVariable Long id) {
        return ResponseEntity.ok(mouvementStockService.getMouvementById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MouvementStockDtoResponse> updateMouvement(@PathVariable Long id, @RequestBody MouvementStockDTO dto) {
        return ResponseEntity.ok(mouvementStockService.updateMouvement(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMouvement(@PathVariable Long id) {
        mouvementStockService.deleteMouvement(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stock/{stockId}")
    public ResponseEntity<List<MouvementStockDtoResponse>> getMouvementsByStock(@PathVariable Long stockId) {
        return ResponseEntity.ok(mouvementStockService.getMouvementsByStockId(stockId));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<MouvementStockDtoResponse>> getMouvementsByType(@PathVariable TypeMouvement type) {
        return ResponseEntity.ok(mouvementStockService.getMouvementsByType(type));
    }

    @GetMapping("/unite/{uniteId}")
    public ResponseEntity<List<MouvementStockDtoResponse>> getMouvementsByUnite(@PathVariable Long uniteId) {
        return ResponseEntity.ok(mouvementStockService.getMouvementsByUniteId(uniteId));
    }

    @GetMapping("/hopital/{hopitalId}")
    public ResponseEntity<List<MouvementStockDtoResponse>> getMouvementsByHopital(@PathVariable Long hopitalId) {
        return ResponseEntity.ok(mouvementStockService.getMouvementsByHopitalId(hopitalId));
    }
}

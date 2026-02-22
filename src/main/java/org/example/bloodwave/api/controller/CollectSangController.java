package org.example.bloodwave.api.controller;


import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.request.CollecteSangDTO;
import org.example.bloodwave.application.dto.response.CollecteSangDtoResponse;
import org.example.bloodwave.application.service.CollecteSangService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collect-sang")
@AllArgsConstructor
public class CollectSangController {

    private final CollecteSangService collecteSangService;


    @PostMapping
    public ResponseEntity<CollecteSangDtoResponse> createCollecte(@RequestBody CollecteSangDTO dto) {
        CollecteSangDtoResponse created = collecteSangService.createCollecte(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollecteSangDtoResponse> updateCollecte(
            @PathVariable Long id,
            @RequestBody CollecteSangDTO dto
    ) {
        CollecteSangDtoResponse updated = collecteSangService.updateCollecte(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollecte(@PathVariable Long id) {
        collecteSangService.deleteCollecte(id);
        return ResponseEntity.noContent().build();
    }

    //

    @GetMapping("/{id}")
    public ResponseEntity<CollecteSangDtoResponse> getCollecteById(@PathVariable Long id) {
        CollecteSangDtoResponse collecte = collecteSangService.getCollecteById(id);
        return ResponseEntity.ok(collecte);
    }

    @GetMapping
    public ResponseEntity<List<CollecteSangDtoResponse>> getAllCollectes() {
        List<CollecteSangDtoResponse> collectes = collecteSangService.getAllCollectes();
        return ResponseEntity.ok(collectes);
    }
}

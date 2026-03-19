package org.example.bloodwave.api.controller;

import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.request.UniteSangDTO;
import org.example.bloodwave.application.dto.response.UniteSangDtoResponse;
import org.example.bloodwave.application.service.UniteSangService;
import org.example.bloodwave.domain.enumeration.StatutUnite;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unites")
@AllArgsConstructor
public class UniteSangController {

    private final UniteSangService uniteSangService;

    @PostMapping
    public ResponseEntity<UniteSangDtoResponse> createUnite(@RequestBody UniteSangDTO dto) {
        return ResponseEntity.ok(uniteSangService.createUnite(dto));
    }

    @GetMapping
    public ResponseEntity<List<UniteSangDtoResponse>> getAllUnites() {
        return ResponseEntity.ok(uniteSangService.getAllUnites());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UniteSangDtoResponse> getUniteById(@PathVariable Long id) {
        return ResponseEntity.ok(uniteSangService.getUniteById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UniteSangDtoResponse> updateUnite(@PathVariable Long id, @RequestBody UniteSangDTO dto) {
        return ResponseEntity.ok(uniteSangService.updateUnite(id, dto));
    }

    @PatchMapping("/{id}/statut")
    public ResponseEntity<UniteSangDtoResponse> updateStatusUnite(
            @PathVariable Long id,
            @RequestParam StatutUnite nouveauStatut
    ) {
        return ResponseEntity.ok(uniteSangService.updateStatusUnite(id, nouveauStatut));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnite(@PathVariable Long id) {
        uniteSangService.deleteUnite(id);
        return ResponseEntity.noContent().build();
    }
}

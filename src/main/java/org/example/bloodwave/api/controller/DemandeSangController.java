package org.example.bloodwave.api.controller;

import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.request.DemandeSangDTO;
import org.example.bloodwave.application.dto.response.DemandeSangDtoResponse;
import org.example.bloodwave.application.service.DemandeSangService;
import org.example.bloodwave.domain.enumeration.StatutDemande;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demandes")
@AllArgsConstructor
public class DemandeSangController {

    private final DemandeSangService demandeSangService;

    @PostMapping
    public ResponseEntity<DemandeSangDtoResponse> create(@RequestBody DemandeSangDTO dto) {
        DemandeSangDtoResponse response = demandeSangService.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DemandeSangDtoResponse>> getAll() {
        List<DemandeSangDtoResponse> list = demandeSangService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemandeSangDtoResponse> getById(@PathVariable Long id) {
        DemandeSangDtoResponse response = demandeSangService.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/demandeur/{demandeurId}")
    public ResponseEntity<List<DemandeSangDtoResponse>> getByDemandeur(@PathVariable Long demandeurId) {
        List<DemandeSangDtoResponse> list = demandeSangService.getByDemandeur(demandeurId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/hopital/{hopitalId}")
    public ResponseEntity<List<DemandeSangDtoResponse>> getByHopital(@PathVariable Long hopitalId) {
        List<DemandeSangDtoResponse> list = demandeSangService.getByHopital(hopitalId);
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}/statut")
    public ResponseEntity<DemandeSangDtoResponse> updateStatut(
            @PathVariable Long id,
            @RequestParam StatutDemande statut) {
        DemandeSangDtoResponse response = demandeSangService.updateStatut(id, statut);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<String> approve(@PathVariable Long id) {
        demandeSangService.approveDemande(id);
        return ResponseEntity.ok("Demande approved successfully");
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<String> reject(@PathVariable Long id) {
        demandeSangService.rejectDemande(id);
        return ResponseEntity.ok("Demande rejected successfully");
    }
}

package org.example.bloodwave.api.controller;


import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.request.DemandeSangDTO;
import org.example.bloodwave.application.dto.response.DemandeSangDtoResponse;
import org.example.bloodwave.application.service.DemandeSangService;
import org.example.bloodwave.domain.enumeration.StatutDemande;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller responsible for managing blood requests.
 *
 * Base URL: /api/demandes
 *
 * Handles:
 * - Creation of blood requests
 * - Retrieval (all, by ID, by demandeur)
 * - Status update
 * - Approval / Rejection
 */
@RestController
@RequestMapping("/api/demandes")
@AllArgsConstructor
public class DemandeSangController {

    /**
     * Service handling business logic for blood requests.
     */
    private final DemandeSangService demandeSangService;

    /**
     * Creates a new blood request.
     */
    @PostMapping
    public ResponseEntity<DemandeSangDtoResponse> create(@RequestBody DemandeSangDTO dto) {
        DemandeSangDtoResponse response = demandeSangService.create(dto);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves all blood requests.
     */
    @GetMapping
    public ResponseEntity<List<DemandeSangDtoResponse>> getAll() {
        List<DemandeSangDtoResponse> list = demandeSangService.getAll();
        return ResponseEntity.ok(list);
    }

    /**
     * Retrieves a blood request by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DemandeSangDtoResponse> getById(@PathVariable Long id) {
        DemandeSangDtoResponse response = demandeSangService.getById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves blood requests by demandeur ID.
     */
    @GetMapping("/demandeur/{demandeurId}")
    public ResponseEntity<List<DemandeSangDtoResponse>> getByDemandeur(@PathVariable Long demandeurId) {
        List<DemandeSangDtoResponse> list =
                demandeSangService.getByDemandeur(demandeurId);
        return ResponseEntity.ok(list);
    }

    /**
     * Updates the status of a blood request.
     */
    @PutMapping("/{id}/statut")
    public ResponseEntity<DemandeSangDtoResponse> updateStatut(
            @PathVariable Long id,
            @RequestParam StatutDemande statut
    ) {
        DemandeSangDtoResponse response =
                demandeSangService.updateStatut(id, statut);
        return ResponseEntity.ok(response);
    }

    /**
     * Approves a blood request.
     */
    @PostMapping("/demandes/{id}/approve")
    public ResponseEntity<String> approve(@PathVariable Long id) {
        demandeSangService.approveDemande(id);
        return ResponseEntity.ok("Demande approved successfully");
    }

    /**
     * Rejects a blood request.
     */
    @PostMapping("/demandes/{id}/reject")
    public ResponseEntity<String> reject(@PathVariable Long id) {
        demandeSangService.rejectDemande(id);
        return ResponseEntity.ok("Demande rejected successfully");
    }
}
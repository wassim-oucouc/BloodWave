package org.example.bloodwave.api.controller;


import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.response.DemandeSangDtoResponse;
import org.example.bloodwave.application.service.DemandeSangService;
import org.example.bloodwave.domain.enumeration.StatutDemande;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hopital")
@AllArgsConstructor
public class HopitalController {

    private final DemandeSangService demandeSangService;


    @GetMapping("/{hopitalId}")
    public ResponseEntity<List<DemandeSangDtoResponse>> getDemandes(@PathVariable Long hopitalId) {
        List<DemandeSangDtoResponse> demandes = demandeSangService.getDemandesByHopital(hopitalId);
        return ResponseEntity.ok(demandes);
    }

    @PutMapping("/{demandeId}/traiter")
    public ResponseEntity<DemandeSangDtoResponse> traiterDemande(
            @PathVariable Long demandeId,
            @RequestParam StatutDemande statut) {

        DemandeSangDtoResponse response = demandeSangService.traiterDemande(demandeId, statut);
        return ResponseEntity.ok(response);
    }
}

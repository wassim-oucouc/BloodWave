package org.example.bloodwave.api.controller;

import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.response.InscriptionCollecteDtoResponse;
import org.example.bloodwave.application.service.InscriptionCollecteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscriptions-collecte")
@AllArgsConstructor
public class InscriptionCollecteController {

    private final InscriptionCollecteService inscriptionCollecteService;

    @GetMapping("/donneur/{donneurId}")
    public ResponseEntity<List<InscriptionCollecteDtoResponse>> getInscriptionsByDonneurId(
            @PathVariable Long donneurId
    ) {
        return ResponseEntity.ok(inscriptionCollecteService.getInscriptionsByDonneurId(donneurId));
    }

    @GetMapping("/collecte/{collectId}")
    public ResponseEntity<List<InscriptionCollecteDtoResponse>> getInscriptionsByCollectId(
            @PathVariable Long collectId
    ) {
        return ResponseEntity.ok(inscriptionCollecteService.getInscriptionsByCollectId(collectId));
    }

    @DeleteMapping("/{inscriptionId}")
    public ResponseEntity<Void> deleteInscriptionById(@PathVariable Long inscriptionId) {
        inscriptionCollecteService.deleteInscriptionById(inscriptionId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/collecte/{collectId}/donneur/{donneurId}")
    public ResponseEntity<Void> deleteInscriptionByCollectAndDonneur(
            @PathVariable Long collectId,
            @PathVariable Long donneurId
    ) {
        inscriptionCollecteService.deleteInscriptionByCollectAndDonneur(collectId, donneurId);
        return ResponseEntity.noContent().build();
    }
}

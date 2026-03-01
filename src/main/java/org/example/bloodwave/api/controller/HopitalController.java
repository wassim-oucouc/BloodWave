package org.example.bloodwave.api.controller;

import lombok.RequiredArgsConstructor;
import org.example.bloodwave.application.dto.response.DonneurDtoResponse;
import org.example.bloodwave.application.dto.response.StockSangDtoResponse;
import org.example.bloodwave.application.service.HopitalService;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller responsible for hospital operations.
 *
 * Base URL: /api/hopital
 *
 * Features:
 * - Get compatible donors suggestions
 * - (Future) Manage hospital stock
 *
 */
@RestController
@RequestMapping("/api/hopital")
@RequiredArgsConstructor
public class HopitalController {

    private final HopitalService hopitalService;

    /**
     * Get compatible donors based on blood group.
     * <p>
     * Example:
     * GET /api/hopital/donneurs-compatibles?groupe=A_POS
     *
     * @param groupe Blood group needed
     * @return List of compatible and available donors
     */
    @GetMapping("/donneurs-compatibles")
    public ResponseEntity<List<DonneurDtoResponse>> getCompatibleDonneurs(
            @RequestParam GroupeSanguin groupe
    ) {

        List<DonneurDtoResponse> donneurs =
                hopitalService.findCompatibleDonneurs(groupe);

        return ResponseEntity.ok(donneurs);
    }

    /**
     * 2 Get hospital blood stock
     */
    @GetMapping("/stock/{hopitalId}")
    public ResponseEntity<List<StockSangDtoResponse>> getStock(@PathVariable("hopitalId") Long hopitalId) {
        return ResponseEntity.ok(
                hopitalService.getStockForConnectedHospital(hopitalId)
        );
    }
}

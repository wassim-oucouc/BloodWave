package org.example.bloodwave.api.controller;

import lombok.RequiredArgsConstructor;
import org.example.bloodwave.application.dto.response.DonDtoResponse;
import org.example.bloodwave.application.dto.response.DonneurDtoResponse;
import org.example.bloodwave.application.dto.response.StockSangDtoResponse;
import org.example.bloodwave.application.dto.response.UniteSangDtoResponse;
import org.example.bloodwave.application.service.DonneurService;
import org.example.bloodwave.application.service.HopitalService;
import org.example.bloodwave.application.service.UniteSangService;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.example.bloodwave.domain.enumeration.StatutUnite;
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
    private final UniteSangService uniteSangService;
    private final DonneurService donneurService;

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
            @RequestParam GroupeSanguin groupe,
            @RequestParam(required = false) String ville
    ) {

        List<DonneurDtoResponse> donneurs =
            hopitalService.findCompatibleDonneurs(groupe, ville);

        return ResponseEntity.ok(donneurs);
    }

    /** Find donors by city: /api/hopital/donneurs/par-ville?city=Casablanca */
    @GetMapping("/donneurs/par-ville")
    public ResponseEntity<List<DonneurDtoResponse>> getDonneursByCity(@RequestParam("city") String city) {
        return ResponseEntity.ok(donneurService.getDonneursByCity(city));
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

    /**
     * Envoie un email à un utilisateur depuis le contexte de l'hôpital.
     *
     * @param userId  L'identifiant de l'utilisateur destinataire.
     * @param subject Le sujet de l'email.
     * @param object  Le contenu du message/email.
     * @return ResponseEntity avec un message de succès si l'email est envoyé.
     */
    @PostMapping("/send-message-to-user/{id}")
    public ResponseEntity<String> sendMessageToUserFromHopital(
            @PathVariable("id") Long userId,
            @RequestParam("subject") String subject,
            @RequestParam("object") String object
    ) {
        hopitalService.sendMessageToUser(subject, object, userId);

        return ResponseEntity.ok("Email sent successfully to user with ID " + userId + " from Hopital");
    }


    @PatchMapping("/unites/{id}/statut")
    public ResponseEntity<UniteSangDtoResponse> updateStatusUnite(
            @PathVariable Long id,
            @RequestParam StatutUnite nouveauStatut) {

        UniteSangDtoResponse unite = uniteSangService.updateStatusUnite(id, nouveauStatut);

        return ResponseEntity.ok(unite);
    }

    /**
     * Get all donations for a specific hospital
     *
     * @param hopitalId The hospital ID
     * @return List of donations for the hospital
     */
    @GetMapping({"/{hopitalId}/donations", "/donations/{hopitalId}"})
    public ResponseEntity<List<DonDtoResponse>> getDonationsByHopital(@PathVariable Long hopitalId) {
        return ResponseEntity.ok(hopitalService.getDonationsByHopitalId(hopitalId));
    }
}

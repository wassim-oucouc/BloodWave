package org.example.bloodwave.api.controller;


import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.request.DonneurUpdateDTO;
import org.example.bloodwave.application.dto.response.CollecteSangDtoResponse;
import org.example.bloodwave.application.dto.response.DonDtoResponse;
import org.example.bloodwave.application.dto.response.DonneurDtoResponse;
import org.example.bloodwave.application.service.CollecteSangService;
import org.example.bloodwave.application.service.DonService;
import org.example.bloodwave.application.service.DonneurService;
import org.example.bloodwave.domain.entity.Donneur;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * REST Controller responsible for donor management and interactions with blood collections.
 */
@RestController
@RequestMapping("/api/donneur")
@AllArgsConstructor
public class DonneurController {

    private final DonneurService donneurService;
    private final DonService donService;
    private final CollecteSangService collecteSangService;

    /** Updates donor blood group only */
    @PutMapping("/{id}/groupe-sanguin")
    public ResponseEntity<DonneurDtoResponse> updateGroupeSanguinById(
            @PathVariable Long id,
            @RequestBody GroupeSanguin groupeSanguin
    ) {
        DonneurDtoResponse donneurDtoResponse = donneurService.updateGroupSanguinById(id, groupeSanguin);
        return ResponseEntity.ok(donneurDtoResponse);
    }

    /** Retrieves donation history for a donor */
    @GetMapping("/donations/{id}")
    public ResponseEntity<List<DonDtoResponse>> getDonationHistoryByDonneurId(@PathVariable Long id) {
        return ResponseEntity.ok(donService.getDonationHistoryById(id));
    }

    /** Donor joins a blood collection */
    @PostMapping("/{donneurId}/collectes/{collecteId}/join")
    public ResponseEntity<CollecteSangDtoResponse> joinCollecte(
            @PathVariable Long donneurId,
            @PathVariable Long collecteId
    ) {
        Donneur donneur = donneurService.findDonneurEntityById(donneurId);
        CollecteSangDtoResponse joined = collecteSangService.joinCollecte(collecteId, donneur);
        return ResponseEntity.ok(joined);
    }

    /** Donor cancels participation in a blood collection */
    @PostMapping("/{donneurId}/collectes/{collecteId}/cancel")
    public ResponseEntity<CollecteSangDtoResponse> cancelParticipation(
            @PathVariable Long donneurId,
            @PathVariable Long collecteId
    ) {
        Donneur donneur = donneurService.findDonneurEntityById(donneurId);
        CollecteSangDtoResponse canceled = collecteSangService.cancelParticipation(collecteId, donneur);
        return ResponseEntity.ok(canceled);
    }

    /** Retrieves blood collection details */
    @GetMapping("/{donneurId}/collectes/{collecteId}")
    public ResponseEntity<CollecteSangDtoResponse> getCollecteDetails(
            @PathVariable Long donneurId,
            @PathVariable Long collecteId
    ) {
        donneurService.findDonneurEntityById(donneurId);
        CollecteSangDtoResponse collecte = collecteSangService.getCollecteById(collecteId);
        return ResponseEntity.ok(collecte);
    }

    /** Filters donors by city */
    @GetMapping("/donneurs/filter/{city}")
    public ResponseEntity<List<DonneurDtoResponse>> filterDoneursByCity(@PathVariable String city) {
        return ResponseEntity.ok(donneurService.getDonneursByCity(city));
    }

    /** Filters donors by city using query param: /api/donneur/by-city?city=Casablanca */
    @GetMapping("/by-city")
    public ResponseEntity<List<DonneurDtoResponse>> findDonneursByCity(@RequestParam("city") String city) {
        return ResponseEntity.ok(donneurService.getDonneursByCity(city));
    }

    @GetMapping("/donneur/{donneurId}")
    public ResponseEntity<DonneurDtoResponse> findDonneurById(@PathVariable Long donneurId)
    {
        return ResponseEntity.ok().body(this.donneurService.findDonneurById(donneurId));
    }

    /** Updates donor personal info (weight, blood group, dates, medical flags) */
    @PutMapping("/{id}")
    public ResponseEntity<DonneurDtoResponse> updateDonneurInfo(
            @PathVariable("id") Long donneurId,
            @RequestBody DonneurUpdateDTO dto
    ) {
        return ResponseEntity.ok(donneurService.updateDonneurInfo(donneurId, dto));
    }
}

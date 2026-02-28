package org.example.bloodwave.api.controller;


import lombok.AllArgsConstructor;
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

@RestController
@RequestMapping("/api/donneur")
@AllArgsConstructor
public class DonneurController {

    public final DonneurService donneurService;
    private final DonService donService;
    private final CollecteSangService collecteSangService;


    @PutMapping("/{id}")
    public ResponseEntity<DonneurDtoResponse> updateGroupeSanguinById(@PathVariable("id") Long id, @RequestBody GroupeSanguin groupeSanguin)
    {
       DonneurDtoResponse donneurDtoResponse =  this.donneurService.updateGroupSanguinById(id,groupeSanguin);

       return ResponseEntity.ok().body(donneurDtoResponse);
    }

    @GetMapping("/donations/{id}")
    public ResponseEntity<List<DonDtoResponse>> getDonationHistoryByDonneurId(@PathVariable("id") Long id)
    {
        return ResponseEntity.ok().body(this.donService.getDonationHistoryById(id));
    }

    @PostMapping("/{donneurId}/collectes/{collecteId}/join")
    public ResponseEntity<CollecteSangDtoResponse> joinCollecte(
            @PathVariable Long donneurId,
            @PathVariable Long collecteId
    ) {
        Donneur donneur = this.donneurService.findDonneurById(donneurId);
        CollecteSangDtoResponse joined = collecteSangService.joinCollecte(collecteId, donneur);
        return ResponseEntity.ok(joined);
    }

    @PostMapping("/{donneurId}/collectes/{collecteId}/cancel")
    public ResponseEntity<CollecteSangDtoResponse> cancelParticipation(
            @PathVariable Long donneurId,
            @PathVariable Long collecteId
    ) {
        Donneur donneur = this.donneurService.findDonneurById(donneurId);
        CollecteSangDtoResponse canceled = collecteSangService.cancelParticipation(collecteId, donneur);
        return ResponseEntity.ok(canceled);
    }

    @GetMapping("/{donneurId}/collectes/{collecteId}")
    public ResponseEntity<CollecteSangDtoResponse> getCollecteDetails(
            @PathVariable Long collecteId
    ) {
        CollecteSangDtoResponse collecte = collecteSangService.getCollecteById(collecteId);
        return ResponseEntity.ok(collecte);
    }


    @GetMapping("/donneurs/filter/{city}")
    public ResponseEntity<List<DonneurDtoResponse>> filterDoneursByCity(@PathVariable("city") String city)
    {
        return ResponseEntity.ok().body(this.donneurService.getDonneursByCity(city));
    }










}

package org.example.bloodwave.api.controller;

import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.request.DemandeurDTO;
import org.example.bloodwave.application.dto.response.DemandeSangDtoResponse;
import org.example.bloodwave.application.dto.response.DemandeurDtoResponse;
import org.example.bloodwave.application.service.DemandeurService;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demandeurs")
@AllArgsConstructor
public class DemandeurController {

    private final DemandeurService demandeurService;

    @PostMapping
    public ResponseEntity<DemandeurDtoResponse> createDemandeur(@RequestBody DemandeurDTO dto) {
        return ResponseEntity.ok(demandeurService.registerDemandeur(dto));
    }

    @GetMapping
    public ResponseEntity<List<DemandeurDtoResponse>> getAllDemandeurs() {
        return ResponseEntity.ok(demandeurService.getAllDemandeurs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemandeurDtoResponse> getDemandeurById(@PathVariable Long id) {
        return ResponseEntity.ok(demandeurService.getDemandeurById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DemandeurDtoResponse> updateDemandeur(@PathVariable Long id, @RequestBody DemandeurDTO dto) {
        return ResponseEntity.ok(demandeurService.updateDemandeur(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDemandeur(@PathVariable Long id) {
        demandeurService.deleteDemandeur(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/demandes")
    public ResponseEntity<List<DemandeSangDtoResponse>> getDemandesByDemandeur(@PathVariable Long id) {
        return ResponseEntity.ok(demandeurService.getDemandesByDemandeurId(id));
    }

    @GetMapping("/groupe-sanguin/{groupeSanguin}")
    public ResponseEntity<List<DemandeurDtoResponse>> getDemandeursByGroupeSanguin(@PathVariable GroupeSanguin groupeSanguin) {
        return ResponseEntity.ok(demandeurService.getDemandeursByGroupeSanguin(groupeSanguin));
    }
}

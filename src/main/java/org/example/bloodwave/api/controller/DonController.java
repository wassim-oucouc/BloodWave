package org.example.bloodwave.api.controller;

import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.request.DonDTO;
import org.example.bloodwave.application.dto.response.DonDtoResponse;
import org.example.bloodwave.application.exceptions.DonNotFoundException;
import org.example.bloodwave.application.service.DonService;
import org.example.bloodwave.domain.enumeration.StatutDon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dons")
public class DonController {

    private final DonService donService;

    @Autowired
    public DonController(DonService donService) {
        this.donService = donService;
    }

    @PostMapping
    public ResponseEntity<DonDtoResponse> createDon(@RequestBody DonDTO dto) {
        DonDtoResponse created = donService.createDonation(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<DonDtoResponse> approveDon(@PathVariable Long id) {
        DonDtoResponse approved = donService.approveDonation(id);
        return ResponseEntity.ok(approved);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<DonDtoResponse> cancelDon(@PathVariable Long id) {
        DonDtoResponse canceled = donService.cancelDonById(id);
        return ResponseEntity.ok(canceled);
    }

    @GetMapping("/status/{statut}")
    public ResponseEntity<List<DonDtoResponse>> getDonsByStatus(@PathVariable StatutDon statut) {
        List<DonDtoResponse> dons = donService.getDonsByStatus(statut);
        return ResponseEntity.ok(dons);
    }

    @GetMapping("/status/{statut}/pageable")
    public ResponseEntity<Page<DonDtoResponse>> getDonsByStatusPageable(
            @RequestParam int size,
            @RequestParam int page,
            @PathVariable StatutDon statut
    ) {
        Page<DonDtoResponse> dons = donService.getDonsByStatusPageable(size, page, statut);
        return ResponseEntity.ok(dons);
    }

    @GetMapping("/history/{donneurId}")
    public ResponseEntity<List<DonDtoResponse>> getDonationHistory(@PathVariable Long donneurId) {
        List<DonDtoResponse> history = donService.getDonationHistoryById(donneurId);
        return ResponseEntity.ok(history);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonDtoResponse> getDonById(@PathVariable Long id) {
        DonDtoResponse don = donService.getDonationHistoryById(id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new DonNotFoundException("Don not found with id " + id));
        return ResponseEntity.ok(don);
    }
}

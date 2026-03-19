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
/**
 * REST Controller responsible for managing blood donations.
 *
 * Base URL: /api/dons
 */
@RestController
@RequestMapping("/api/dons")
public class DonController {

    private final DonService donService;

    @Autowired
    public DonController(DonService donService) {
        this.donService = donService;
    }

    /** Creates a new donation */
    @PostMapping
    public ResponseEntity<DonDtoResponse> createDon(@RequestBody DonDTO dto) {
        DonDtoResponse created = donService.createDonation(dto);
        return ResponseEntity.ok(created);
    }

    /** Approves a donation by ID */
    @PutMapping("/{id}/approve")
    public ResponseEntity<DonDtoResponse> approveDon(@PathVariable Long id) {
        DonDtoResponse approved = donService.approveDonation(id);
        return ResponseEntity.ok(approved);
    }

    /** Cancels a donation by ID */
    @PutMapping("/{id}/cancel")
    public ResponseEntity<DonDtoResponse> cancelDon(@PathVariable Long id) {
        DonDtoResponse canceled = donService.cancelDonById(id);
        return ResponseEntity.ok(canceled);
    }

    /** Retrieves all donations with the specified status */
    @GetMapping("/status/{statut}")
    public ResponseEntity<List<DonDtoResponse>> getDonsByStatus(@PathVariable StatutDon statut) {
        List<DonDtoResponse> dons = donService.getDonsByStatus(statut);
        return ResponseEntity.ok(dons);
    }

    /** Retrieves donations with the specified status, pageable */
    @GetMapping("/status/{statut}/pageable")
    public ResponseEntity<Page<DonDtoResponse>> getDonsByStatusPageable(
            @RequestParam int size,
            @RequestParam int page,
            @PathVariable StatutDon statut
    ) {
        Page<DonDtoResponse> dons = donService.getDonsByStatusPageable(size, page, statut);
        return ResponseEntity.ok(dons);
    }

    /** Retrieves donation history for a donor */
    @GetMapping("/history/{donneurId}")
    public ResponseEntity<List<DonDtoResponse>> getDonationHistory(@PathVariable Long donneurId) {
        List<DonDtoResponse> history = donService.getDonationHistoryById(donneurId);
        return ResponseEntity.ok(history);
    }

    /** Retrieves a donation by ID */
    @GetMapping("/{id}")
    public ResponseEntity<DonDtoResponse> getDonById(@PathVariable Long id) {
        DonDtoResponse don = donService.getDonById(id);
        return ResponseEntity.ok(don);
    }
}
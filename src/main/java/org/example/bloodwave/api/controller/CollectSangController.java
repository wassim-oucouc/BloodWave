package org.example.bloodwave.api.controller;


import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.request.CollecteSangDTO;
import org.example.bloodwave.application.dto.response.CollecteSangDtoResponse;
import org.example.bloodwave.application.service.CollecteSangService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * REST Controller responsible for managing blood collection operations.
 *
 * Base URL: /api/collect-sang
 *
 * Provides CRUD endpoints for blood collection management.
 */
@RestController
@RequestMapping("/api/collect-sang")
@AllArgsConstructor
public class CollectSangController {

    /**
     * Service handling business logic for blood collections.
     */
    private final CollecteSangService collecteSangService;

    /**
     * Creates a new blood collection.
     *
     * @param dto the blood collection request data
     * @return the created blood collection
     */
    @PostMapping
    public ResponseEntity<CollecteSangDtoResponse> createCollecte(@RequestBody CollecteSangDTO dto) {
        CollecteSangDtoResponse created = collecteSangService.createCollecte(dto);
        return ResponseEntity.ok(created);
    }

    /**
     * Updates an existing blood collection.
     *
     * @param id  the ID of the blood collection to update
     * @param dto the updated blood collection data
     * @return the updated blood collection
     */
    @PutMapping("/{id}")
    public ResponseEntity<CollecteSangDtoResponse> updateCollecte(
            @PathVariable Long id,
            @RequestBody CollecteSangDTO dto
    ) {
        CollecteSangDtoResponse updated = collecteSangService.updateCollecte(id, dto);
        return ResponseEntity.ok(updated);
    }

    /**
     * Deletes a blood collection by ID.
     *
     * @param id the ID of the blood collection
     * @return HTTP 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollecte(@PathVariable Long id) {
        collecteSangService.deleteCollecte(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves a blood collection by ID.
     *
     * @param id the ID of the blood collection
     * @return the blood collection
     */
    @GetMapping("/{id}")
    public ResponseEntity<CollecteSangDtoResponse> getCollecteById(@PathVariable Long id) {
        CollecteSangDtoResponse collecte = collecteSangService.getCollecteById(id);
        return ResponseEntity.ok(collecte);
    }

    /**
     * Retrieves all blood collections.
     *
     * @return list of all blood collections
     */
    @GetMapping
    public ResponseEntity<List<CollecteSangDtoResponse>> getAllCollectes() {
        List<CollecteSangDtoResponse> collectes = collecteSangService.getAllCollectes();
        return ResponseEntity.ok(collectes);
    }
}
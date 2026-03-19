package org.example.bloodwave.api.controller;

import org.example.bloodwave.application.dto.request.UtilisateurDTO;
import org.example.bloodwave.application.dto.request.UtilisateurUpdateDTO;
import org.example.bloodwave.application.dto.response.UtilisateurDtoResponse;
import org.example.bloodwave.application.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;
/**
 * REST controller for managing utilisateurs (users).
 * Provides endpoints to create, update, delete, ban, activate, and retrieve users.
 */
@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    /**
     * Constructor to inject UtilisateurService.
     *
     * @param utilisateurService the service that handles user business logic
     */
    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    /**
     * Create a new utilisateur.
     *
     * @param dto the user data to create
     * @return ResponseEntity containing the created user
     */
    @PostMapping
    public ResponseEntity<UtilisateurDtoResponse> createUtilisateur(@RequestBody UtilisateurDTO dto) {
        UtilisateurDtoResponse created = utilisateurService.createUtilisateur(dto);
        return ResponseEntity.ok(created);
    }

    /**
     * Update an existing utilisateur by ID.
     *
     * @param id  the ID of the user to update
     * @param dto the updated user data
     * @return ResponseEntity containing the updated user
     */
    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDtoResponse> updateUtilisateur(
            @PathVariable Long id,
            @RequestBody UtilisateurUpdateDTO dto
    ) {
        UtilisateurDtoResponse updated = utilisateurService.updateUtilisateurById(id, dto);
        return ResponseEntity.ok(updated);
    }

    /**
     * Delete a utilisateur by ID.
     *
     * @param id the ID of the user to delete
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateurById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Ban a utilisateur by ID.
     *
     * @param id the ID of the user to ban
     * @return ResponseEntity containing the banned user
     */
    @PutMapping("/{id}/ban")
    public ResponseEntity<UtilisateurDtoResponse> banUtilisateur(@PathVariable Long id) {
        UtilisateurDtoResponse banned = utilisateurService.banisseUtilisateurById(id);
        return ResponseEntity.ok(banned);
    }

    /**
     * Activate a previously banned utilisateur by ID.
     *
     * @param id the ID of the user to activate
     * @return ResponseEntity containing the activated user
     */
    @PutMapping("/{id}/activate")
    public ResponseEntity<UtilisateurDtoResponse> activateUtilisateur(@PathVariable Long id) {
        UtilisateurDtoResponse activated = utilisateurService.activateUtilisateurById(id);
        return ResponseEntity.ok(activated);
    }

    /**
     * Get a list of all utilisateurs.
     *
     * @return ResponseEntity containing a list of all users
     */
    @GetMapping
    public ResponseEntity<List<UtilisateurDtoResponse>> getAllUtilisateurs() {
        List<UtilisateurDtoResponse> utilisateurs = utilisateurService.getAllUtilisateurs();
        return ResponseEntity.ok(utilisateurs);
    }

    /**
     * Get a single utilisateur by ID.
     *
     * @param id the ID of the user to retrieve
     * @return ResponseEntity containing the requested user
     * @throws UserNotFoundException if no user is found with the given ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDtoResponse> getUtilisateurById(@PathVariable Long id) {
        UtilisateurDtoResponse utilisateur = utilisateurService.getUtilisateurById(id);
        return ResponseEntity.ok(utilisateur);
    }
}
package org.example.bloodwave.api.controller;

import org.example.bloodwave.application.dto.request.UtilisateurDTO;
import org.example.bloodwave.application.dto.response.UtilisateurDtoResponse;
import org.example.bloodwave.application.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
/**
 * REST Controller responsible for managing user profiles.
 */
@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final UtilisateurService utilisateurService;

    public ProfileController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    /** Retrieves a user profile by ID */
    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDtoResponse> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok(utilisateurService.getUtilisateurById(id));
    }

    /** Updates the user profile by ID */
    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDtoResponse> updateProfile(
            @PathVariable Long id,
            @RequestBody UtilisateurDTO dto
    ) {
        return ResponseEntity.ok(utilisateurService.updateUtilisateurById(id, dto));
    }

    /** Changes the user's password */
    @PutMapping("/password")
    public ResponseEntity<String> changePassword(
            @PathVariable Long id,
            @RequestBody Map<String, String> passwords
    ) {
        utilisateurService.changePassword(
                id,
                passwords.get("oldPassword"),
                passwords.get("newPassword")
        );
        return ResponseEntity.ok("Password updated successfully");
    }
}
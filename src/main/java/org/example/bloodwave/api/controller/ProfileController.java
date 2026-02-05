package org.example.bloodwave.api.controller;

import org.example.bloodwave.application.dto.request.UtilisateurDTO;
import org.example.bloodwave.application.dto.response.UtilisateurDtoResponse;
import org.example.bloodwave.application.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final UtilisateurService utilisateurService;

    public ProfileController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDtoResponse> getProfile(@PathVariable("id") Long id) {
        return ResponseEntity.ok(utilisateurService.getUtilisateurById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDtoResponse> updateProfile(
            @PathVariable("id") Long id,
            @RequestBody UtilisateurDTO dto
    ) {
        return ResponseEntity.ok(utilisateurService.updateUtilisateurById(id, dto));
    }

    @PutMapping("/password")
    public ResponseEntity<String> changePassword(
            @PathVariable("id") Long id,
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

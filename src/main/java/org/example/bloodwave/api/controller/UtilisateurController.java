package org.example.bloodwave.api.controller;

import org.example.bloodwave.application.dto.request.UtilisateurDTO;
import org.example.bloodwave.application.dto.response.UtilisateurDtoResponse;
import org.example.bloodwave.application.exceptions.UserNotFoundException;
import org.example.bloodwave.application.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping
    public ResponseEntity<UtilisateurDtoResponse> createUtilisateur(@RequestBody UtilisateurDTO dto) {
        UtilisateurDtoResponse created = utilisateurService.createUtilisateur(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDtoResponse> updateUtilisateur(
            @PathVariable Long id,
            @RequestBody UtilisateurDTO dto
    ) {
        UtilisateurDtoResponse updated = utilisateurService.updateUtilisateurById(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateurById(id);
        return ResponseEntity.noContent().build(); 
    }

    @PutMapping("/{id}/ban")
    public ResponseEntity<UtilisateurDtoResponse> banUtilisateur(@PathVariable Long id) {
        UtilisateurDtoResponse banned = utilisateurService.banisseUtilisateurById(id);
        return ResponseEntity.ok(banned);
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<UtilisateurDtoResponse> activateUtilisateur(@PathVariable Long id) {
        UtilisateurDtoResponse activated = utilisateurService.activateUtilisateurById(id);
        return ResponseEntity.ok(activated);
    }

    @GetMapping
    public ResponseEntity<List<UtilisateurDtoResponse>> getAllUtilisateurs() {
        List<UtilisateurDtoResponse> utilisateurs = utilisateurService.getAllUtilisateurs();
        return ResponseEntity.ok(utilisateurs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDtoResponse> getUtilisateurById(@PathVariable Long id) {
        UtilisateurDtoResponse utilisateur = utilisateurService
                .getAllUtilisateurs()
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("Utilisateur not found with id " + id));
        return ResponseEntity.ok(utilisateur);
    }
}

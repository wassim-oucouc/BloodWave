package org.example.bloodwave.application.service;


import org.example.bloodwave.application.dto.request.UtilisateurDTO;
import org.example.bloodwave.application.dto.response.UtilisateurDtoResponse;
import org.example.bloodwave.domain.entity.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UtilisateurService {

    public UtilisateurDtoResponse createUtilisateur(UtilisateurDTO utilisateurDTO);
    public UtilisateurDtoResponse updateUtilisateurById(Long id, UtilisateurDTO utilisateurDTO);
    public void deleteUtilisateurById(Long id);
    public UtilisateurDtoResponse banisseUtilisateurById(Long id);
    public List<UtilisateurDtoResponse> getAllUtilisateurs();
    public UtilisateurDtoResponse activateUtilisateurById(Long id);
}

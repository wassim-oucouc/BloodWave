package org.example.bloodwave.application.service.impl;

import org.example.bloodwave.application.dto.request.UtilisateurDTO;
import org.example.bloodwave.application.dto.response.UtilisateurDtoResponse;
import org.example.bloodwave.application.exceptions.PasswordNotMatchException;
import org.example.bloodwave.application.mapper.UtilisateurMapper;
import org.example.bloodwave.application.service.UtilisateurService;
import org.example.bloodwave.domain.entity.Utilisateur;
import org.example.bloodwave.domain.repository.UtilisateurRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, UtilisateurMapper utilisateurMapper,
            PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurMapper = utilisateurMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UtilisateurDtoResponse createUtilisateur(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = this.utilisateurMapper.toEntity(utilisateurDTO);
        Utilisateur utilisateurCreated = this.utilisateurRepository.save(utilisateur);
        return this.utilisateurMapper.toDtoResponse(utilisateurCreated);

    }

    public UtilisateurDtoResponse updateUtilisateurById(Long id, UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateurFound = this.utilisateurRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("user not found by id : " + id));
        Utilisateur updated = this.utilisateurMapper.toEntity(utilisateurDTO);

        updated.setId(utilisateurFound.getId());

        Utilisateur utilisateurUpdated = this.utilisateurRepository.save(updated);

        return this.utilisateurMapper.toDtoResponse(utilisateurUpdated);

    }

    public void deleteUtilisateurById(Long id) {
        Utilisateur utilisateurFound = this.utilisateurRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("user not found by id : " + id));

        this.utilisateurRepository.delete(utilisateurFound);
    }

    public UtilisateurDtoResponse banisseUtilisateurById(Long id) {
        Utilisateur utilisateurFound = this.utilisateurRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("user not found by id : " + id));

        utilisateurFound.setActif(false);

        this.utilisateurRepository.save(utilisateurFound);

        return this.utilisateurMapper.toDtoResponse(utilisateurFound);
    }

    public List<UtilisateurDtoResponse> getAllUtilisateurs() {
        return this.utilisateurRepository.findAll().stream().map(utilisateurMapper::toDtoResponse).toList();
    }

    public UtilisateurDtoResponse activateUtilisateurById(Long id) {
        Utilisateur utilisateurFound = this.utilisateurRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("user not found by id : " + id));

        utilisateurFound.setActif(true);

        return this.utilisateurMapper.toDtoResponse(utilisateurFound);
    }

    public UtilisateurDtoResponse getUtilisateurById(Long id) {
        Utilisateur utilisateurFound = this.utilisateurRepository
                .findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("user not found by id : " + id));

        return this.utilisateurMapper.toDtoResponse(utilisateurFound);

    }

    public void changePassword(Long id, String oldPassword, String newPassword) {
        Utilisateur utilisateurFound = this.utilisateurRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("user not found by id : " + id));

        boolean check = this.passwordEncoder
                .matches(oldPassword, utilisateurFound.getMotDePasse());

        if (check) {
            String PasswordEncoded = this.passwordEncoder.encode(newPassword);
            utilisateurFound.setMotDePasse(PasswordEncoded);
            this.utilisateurRepository.save(utilisateurFound);
        } else {
            throw new PasswordNotMatchException("password you entered not matched");
        }
    }
}

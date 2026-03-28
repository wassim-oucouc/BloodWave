package org.example.bloodwave.application.service.impl;


import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.request.DemandeurDTO;
import org.example.bloodwave.application.dto.response.DemandeSangDtoResponse;
import org.example.bloodwave.application.dto.response.DemandeurDtoResponse;
import org.example.bloodwave.application.exceptions.DemandeurNotFoundException;
import org.example.bloodwave.application.mapper.DemandeSangMapper;
import org.example.bloodwave.domain.entity.Demandeur;
import org.example.bloodwave.application.mapper.DemandeurMapper;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.example.bloodwave.domain.enumeration.RoleType;
import org.example.bloodwave.domain.repository.DemandeurRepository;
import org.example.bloodwave.application.service.DemandeurService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class DemandeurServiceImpl implements DemandeurService {

    private final DemandeurRepository demandeurRepository;
    private final PasswordEncoder passwordEncoder;
    private final DemandeurMapper demandeurMapper;
    private final DemandeSangMapper demandeSangMapper;

    @Override
    public DemandeurDtoResponse registerDemandeur(DemandeurDTO dto) {
        Demandeur demandeur = demandeurMapper.toEntity(dto);
        demandeur.setRole(RoleType.DEMANDEUR);
        demandeur.setActif(true);
        demandeur.setDateCreation(LocalDateTime.now());
        
        String passwordHashed = passwordEncoder.encode(demandeur.getMotDePasse());
        demandeur.setMotDePasse(passwordHashed);

        Demandeur demandeurCreated = demandeurRepository.save(demandeur);
        return demandeurMapper.toDtoResponse(demandeurCreated);
    }

    @Override
    public DemandeurDtoResponse getDemandeurById(Long id) {
        Demandeur demandeur = demandeurRepository.findById(id)
                .orElseThrow(() -> new DemandeurNotFoundException("Demandeur not found with id " + id));
        return demandeurMapper.toDtoResponse(demandeur);
    }

    @Override
    public List<DemandeurDtoResponse> getAllDemandeurs() {
        return demandeurRepository.findAll()
                .stream()
                .map(demandeurMapper::toDtoResponse)
                .toList();
    }

    @Override
    public DemandeurDtoResponse updateDemandeur(Long id, DemandeurDTO dto) {
        Demandeur demandeur = demandeurRepository.findById(id)
                .orElseThrow(() -> new DemandeurNotFoundException("Demandeur not found with id " + id));

        if (dto.getNom() != null) {
            demandeur.setNom(dto.getNom());
        }
        if (dto.getPrenom() != null) {
            demandeur.setPrenom(dto.getPrenom());
        }
        if (dto.getEmail() != null) {
            demandeur.setEmail(dto.getEmail());
        }
        if (dto.getTelephone() != null) {
            demandeur.setTelephone(dto.getTelephone());
        }
        if (dto.getAdresse() != null) {
            demandeur.setAdresse(dto.getAdresse());
        }
        if (dto.getVille() != null) {
            demandeur.setVille(dto.getVille());
        }
        if (dto.getGroupeSanguin() != null) {
            demandeur.setGroupeSanguin(dto.getGroupeSanguin());
        }
        if (dto.getMotDePasse() != null && !dto.getMotDePasse().isEmpty()) {
            String passwordHashed = passwordEncoder.encode(dto.getMotDePasse());
            demandeur.setMotDePasse(passwordHashed);
        }

        Demandeur updated = demandeurRepository.save(demandeur);
        return demandeurMapper.toDtoResponse(updated);
    }

    @Override
    public void deleteDemandeur(Long id) {
        Demandeur demandeur = demandeurRepository.findById(id)
                .orElseThrow(() -> new DemandeurNotFoundException("Demandeur not found with id " + id));
        demandeurRepository.delete(demandeur);
    }

    @Override
    public List<DemandeSangDtoResponse> getDemandesByDemandeurId(Long demandeurId) {
        Demandeur demandeur = demandeurRepository.findById(demandeurId)
                .orElseThrow(() -> new DemandeurNotFoundException("Demandeur not found with id " + demandeurId));
        
        return demandeur.getDemandeSangs()
                .stream()
                .map(demandeSangMapper::toDtoResponse)
                .toList();
    }

    @Override
    public List<DemandeurDtoResponse> getDemandeursByGroupeSanguin(GroupeSanguin groupeSanguin) {
        return demandeurRepository.findAll()
                .stream()
                .filter(d -> d.getGroupeSanguin() == groupeSanguin)
                .map(demandeurMapper::toDtoResponse)
                .toList();
    }
}

package org.example.bloodwave.application.service.impl;

import org.example.bloodwave.application.dto.request.UtilisateurDTO;
import org.example.bloodwave.application.dto.response.UtilisateurDtoResponse;
import org.example.bloodwave.application.mapper.UtilisateurMapper;
import org.example.bloodwave.application.service.UtilisateurService;
import org.example.bloodwave.domain.entity.Utilisateur;
import org.example.bloodwave.domain.repository.UtilisateurRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, UtilisateurMapper utilisateurMapper) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurMapper = utilisateurMapper;
    }

    public UtilisateurDtoResponse createUtilisateur(UtilisateurDTO utilisateurDTO)
    {
   Utilisateur utilisateur =      this.utilisateurMapper.toEntity(utilisateurDTO);
  Utilisateur utilisateurCreated =  this.utilisateurRepository.save(utilisateur);
  return this.utilisateurMapper.toDtoResponse(utilisateurCreated);

    }
    public UtilisateurDtoResponse updateUtilisateurById(Long id,UtilisateurDTO utilisateurDTO)
    {
       Utilisateur utilisateurFound =  this.utilisateurRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("user not found by id : " + id));
    Utilisateur updated = this.utilisateurMapper.toEntity(utilisateurDTO);

    updated.setId(utilisateurFound.getId());

   Utilisateur utilisateurUpdated =  this.utilisateurRepository.save(updated);

   return this.utilisateurMapper.toDtoResponse(utilisateurUpdated);


    }
    public void deleteUtilisateurById(Long id)
    {
        Utilisateur utilisateurFound =  this.utilisateurRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("user not found by id : " + id));

        this.utilisateurRepository.delete(utilisateurFound);
    }
    public UtilisateurDtoResponse banisseUtilisateurById(Long id)
    {
        Utilisateur utilisateurFound =  this.utilisateurRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("user not found by id : " + id));

        utilisateurFound.setActif(false);

        return this.utilisateurMapper.toDtoResponse(utilisateurFound);


    }

    public List<UtilisateurDtoResponse> getAllUtilisateurs()
    {
      return  this.utilisateurRepository.findAll().stream().map(utilisateurMapper::toDtoResponse).toList();
    }

    public UtilisateurDtoResponse activateUtilisateurById(Long id)
    {
        Utilisateur utilisateurFound =  this.utilisateurRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("user not found by id : " + id));

        utilisateurFound.setActif(true);

        return this.utilisateurMapper.toDtoResponse(utilisateurFound);


    }
}

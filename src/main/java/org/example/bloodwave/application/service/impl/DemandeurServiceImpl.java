package org.example.bloodwave.application.service.impl;


import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.request.DemandeurDTO;
import org.example.bloodwave.application.dto.response.DemandeurDtoResponse;
import org.example.bloodwave.domain.entity.Demandeur;
import org.example.bloodwave.application.mapper.DemandeurMapper;
import org.example.bloodwave.domain.enumeration.RoleType;
import org.example.bloodwave.domain.repository.DemandeurRepository;
import org.example.bloodwave.application.service.DemandeurService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DemandeurServiceImpl implements DemandeurService {

    public DemandeurRepository demandeurRepository;
    public PasswordEncoder passwordEncoder;
    public DemandeurMapper demandeurMapper;



    public DemandeurDtoResponse registerDemandeur(DemandeurDTO dto)
    {
        Demandeur demandeur  = this.demandeurMapper.toEntity(dto);

        demandeur.setRole(RoleType.DEMANDEUR);

        String passwordHashed = this.passwordEncoder.encode(demandeur.getMotDePasse());

        demandeur.setMotDePasse(passwordHashed);

        Demandeur demandeurCreated =  this.demandeurRepository.save(demandeur);
        return this.demandeurMapper.toDtoResponse(demandeurCreated);
    }
}

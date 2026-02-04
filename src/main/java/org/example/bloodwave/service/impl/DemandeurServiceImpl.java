package org.example.bloodwave.service.impl;


import lombok.AllArgsConstructor;
import org.example.bloodwave.dto.request.DemandeurDTO;
import org.example.bloodwave.dto.response.DemandeurDtoResponse;
import org.example.bloodwave.entity.Demandeur;
import org.example.bloodwave.mapper.DemandeurMapper;
import org.example.bloodwave.repository.DemandeurRepository;
import org.example.bloodwave.service.DemandeurService;
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

        String passwordHashed = this.passwordEncoder.encode(demandeur.getMotDePasse());

        demandeur.setMotDePasse(passwordHashed);

        Demandeur demandeurCreated =  this.demandeurRepository.save(demandeur);
        return this.demandeurMapper.toDtoResponse(demandeurCreated);
    }
}

package org.example.bloodwave.application.service.impl;

import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.request.DonneurDTO;
import org.example.bloodwave.application.dto.response.DonneurDtoResponse;
import org.example.bloodwave.domain.entity.Donneur;
import org.example.bloodwave.application.mapper.DonneurMapper;
import org.example.bloodwave.domain.repository.DonneurRepository;
import org.example.bloodwave.application.service.DonneurService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class DonneurServiceImpl implements DonneurService {


    public DonneurMapper donneurMapper;
    public PasswordEncoder passwordEncoder;
    public DonneurRepository donneurAuthRepository;



    public DonneurDtoResponse registerDonneur(DonneurDTO dto)
    {
        Donneur donneur  = this.donneurMapper.toEntity(dto);

        String passwordHashed = this.passwordEncoder.encode(donneur.getMotDePasse());

        donneur.setMotDePasse(passwordHashed);

        Donneur donneurCreated =  this.donneurAuthRepository.save(donneur);
        return this.donneurMapper.toDtoResponse(donneurCreated);
    }

}

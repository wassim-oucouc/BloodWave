package org.example.bloodwave.service.impl;

import lombok.AllArgsConstructor;
import org.example.bloodwave.dto.request.DonneurDTO;
import org.example.bloodwave.dto.response.DonneurDtoResponse;
import org.example.bloodwave.entity.Donneur;
import org.example.bloodwave.mapper.DonneurMapper;
import org.example.bloodwave.repository.DonneurRepository;
import org.example.bloodwave.service.DonneurService;
import org.springframework.beans.factory.annotation.Autowired;
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

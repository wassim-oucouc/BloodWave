package org.example.bloodwave.service.impl;

import org.example.bloodwave.dto.request.HopitalDTO;
import org.example.bloodwave.dto.response.HopitalDtoResponse;
import org.example.bloodwave.entity.Hopital;
import org.example.bloodwave.mapper.HopitalMapper;
import org.example.bloodwave.repository.HopitalRepository;
import org.example.bloodwave.service.HopitalService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class HopitalServiceImpl implements HopitalService {


    public HopitalMapper hopitalMapper;
    public PasswordEncoder passwordEncoder;
    public HopitalRepository hopitalAuthRepository;



    public HopitalDtoResponse registerHopital(HopitalDTO dto)
    {
        Hopital hopital  = this.hopitalMapper.toEntity(dto);

        String passwordHashed = this.passwordEncoder.encode(hopital.getMotDePasse());

        hopital.setMotDePasse(passwordHashed);

        Hopital hopitalCreated =  this.hopitalAuthRepository.save(hopital);

        return this.hopitalMapper.toDtoResponse(hopitalCreated);
    }
}

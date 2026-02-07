package org.example.bloodwave.application.service.impl;

import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.request.DonneurDTO;
import org.example.bloodwave.application.dto.response.DonneurDtoResponse;
import org.example.bloodwave.application.exceptions.DonneurNotFoundException;
import org.example.bloodwave.domain.entity.Donneur;
import org.example.bloodwave.application.mapper.DonneurMapper;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.example.bloodwave.domain.repository.DonneurRepository;
import org.example.bloodwave.application.service.DonneurService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class DonneurServiceImpl implements DonneurService {


    public DonneurMapper donneurMapper;
    public PasswordEncoder passwordEncoder;
    public DonneurRepository donneurRepository;



    public DonneurDtoResponse registerDonneur(DonneurDTO dto)
    {
        Donneur donneur  = this.donneurMapper.toEntity(dto);

        String passwordHashed = this.passwordEncoder.encode(donneur.getMotDePasse());

        donneur.setMotDePasse(passwordHashed);

        Donneur donneurCreated =  this.donneurRepository.save(donneur);
        return this.donneurMapper.toDtoResponse(donneurCreated);
    }

    public DonneurDtoResponse updateGroupSanguinById(Long id, GroupeSanguin groupeSanguin)
    {
      Donneur donneurFound =   this.donneurRepository.findById(id).orElseThrow(() -> new DonneurNotFoundException("donneur not found by id : " + id));

      donneurFound.setGroupeSanguin(groupeSanguin);

      Donneur donneurUpdated = this.donneurRepository.save(donneurFound);

      return this.donneurMapper.toDtoResponse(donneurUpdated);
    }

    public Donneur findDonneurById(Long id)
    {
        return   this.donneurRepository
                .findById(id)
                .orElseThrow(()
                        -> new DonneurNotFoundException("donneur not found by id : " + id));

    }

}

package org.example.bloodwave.application.service;


import org.example.bloodwave.application.dto.request.DonneurDTO;
import org.example.bloodwave.application.dto.request.DonneurUpdateDTO;
import org.example.bloodwave.application.dto.response.DonneurDtoResponse;
import org.example.bloodwave.domain.entity.Donneur;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DonneurService{
    public DonneurDtoResponse registerDonneur(DonneurDTO dto);
    public DonneurDtoResponse updateGroupSanguinById(Long id, GroupeSanguin groupeSanguin);
    public DonneurDtoResponse updateDonneurInfo(Long id, DonneurUpdateDTO dto);
    public DonneurDtoResponse findDonneurById(Long id);
    public boolean isEligible(Long id);
    public List<DonneurDtoResponse> getDonneursByCity(String city);
    public Donneur findDonneurEntityById(Long donneurId);

}

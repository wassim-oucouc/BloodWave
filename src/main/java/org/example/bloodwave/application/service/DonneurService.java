package org.example.bloodwave.application.service;


import org.example.bloodwave.application.dto.request.DonneurDTO;
import org.example.bloodwave.application.dto.response.DonneurDtoResponse;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.springframework.stereotype.Service;

@Service
public interface DonneurService{
    public DonneurDtoResponse registerDonneur(DonneurDTO dto);
    public DonneurDtoResponse updateGroupSanguinById(Long id, GroupeSanguin groupeSanguin);
    public
}

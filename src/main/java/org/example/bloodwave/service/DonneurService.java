package org.example.bloodwave.service;


import org.example.bloodwave.dto.request.DonneurDTO;
import org.example.bloodwave.dto.response.DonneurDtoResponse;
import org.springframework.stereotype.Service;

@Service
public interface DonneurService{
    public DonneurDtoResponse registerDonneur(DonneurDTO dto);
}

package org.example.bloodwave.application.service;


import org.example.bloodwave.application.dto.request.DonneurDTO;
import org.example.bloodwave.application.dto.response.DonneurDtoResponse;
import org.springframework.stereotype.Service;

@Service
public interface DonneurService{
    public DonneurDtoResponse registerDonneur(DonneurDTO dto);
}

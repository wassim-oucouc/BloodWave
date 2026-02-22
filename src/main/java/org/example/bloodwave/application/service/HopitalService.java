package org.example.bloodwave.application.service;


import org.example.bloodwave.application.dto.request.HopitalDTO;
import org.example.bloodwave.application.dto.response.HopitalDtoResponse;
import org.springframework.stereotype.Service;

@Service
public interface HopitalService {

    public HopitalDtoResponse registerHopital(HopitalDTO dto);

}

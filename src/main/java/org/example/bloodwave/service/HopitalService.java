package org.example.bloodwave.service;


import org.example.bloodwave.dto.request.HopitalDTO;
import org.example.bloodwave.dto.response.HopitalDtoResponse;
import org.springframework.stereotype.Service;

@Service
public interface HopitalService {

    public HopitalDtoResponse registerHopital(HopitalDTO dto);

}

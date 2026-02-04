package org.example.bloodwave.service.impl;

import org.example.bloodwave.service.TokenGeneratorService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TokenGeneratorServiceImpl implements TokenGeneratorService {

    public String generateToken()
    {
        return  UUID.randomUUID().toString();
    }


    public LocalDateTime generateExpiry()
    {
        return LocalDateTime.now().plusMinutes(15);

    }
}

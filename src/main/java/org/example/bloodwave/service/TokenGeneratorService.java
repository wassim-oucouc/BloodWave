package org.example.bloodwave.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface TokenGeneratorService {

    public String generateToken();
    public LocalDateTime generateExpiry();

}

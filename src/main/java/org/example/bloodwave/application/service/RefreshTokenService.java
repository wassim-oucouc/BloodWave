package org.example.bloodwave.application.service;

import org.example.bloodwave.domain.entity.RefreshToken;
import org.springframework.stereotype.Service;

@Service
public interface RefreshTokenService {

    public RefreshToken create(String email);

    public RefreshToken verify(String token);

    public void deleteByEmail(String email);
}

package org.example.bloodwave.service;

import org.example.bloodwave.entity.Utilisateur;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    public Utilisateur getUserByEmail(String email);
    public void resetPasswordByEmail(String email);
    public void resetPasswordByToken(String token,String password);

}

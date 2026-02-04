package org.example.bloodwave.service.impl;

import org.example.bloodwave.dto.response.UtilisateurDtoResponse;
import org.example.bloodwave.entity.PasswordResetToken;
import org.example.bloodwave.entity.Utilisateur;
import org.example.bloodwave.exceptions.PasswordResetExpiredException;
import org.example.bloodwave.exceptions.PasswordResetTokenNotFoundException;
import org.example.bloodwave.exceptions.UserNotFoundException;
import org.example.bloodwave.repository.PasswordResetTokenRepository;
import org.example.bloodwave.repository.UtilisateurRepository;
import org.example.bloodwave.service.AuthService;
import org.example.bloodwave.service.EmailService;
import org.example.bloodwave.service.TokenGeneratorService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {
    private final UtilisateurRepository utilisateurRepository;
    private final EmailService emailService;
    private final TokenGeneratorService tokenGeneratorService;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UtilisateurRepository utilisateurRepository, EmailService emailService, TokenGeneratorService tokenGeneratorService, PasswordResetTokenRepository passwordResetTokenRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.emailService = emailService;
        this.tokenGeneratorService = tokenGeneratorService;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Utilisateur getUserByEmail(String email)
    {
        return   this.utilisateurRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("not found user with email" + email));

    }

    public void resetPasswordByEmail(String email)
    {
        Utilisateur utilisateur = this.utilisateurRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("user  not exists by email : " + email));

       String token = this.tokenGeneratorService.generateToken();

        LocalDateTime experation = this.tokenGeneratorService.generateExpiry();

        PasswordResetToken passwordResetToken = PasswordResetToken.builder()
                .token(token)
                .user(utilisateur)
                .expiryDate(experation)
                .build();

        this.passwordResetTokenRepository.save(passwordResetToken);

       this.emailService.sendResetPasswordEmail(email,token);

    }

    public void resetPasswordByToken(String token,String password)
    {
      PasswordResetToken passwordResetToken  =   this.passwordResetTokenRepository
              .getPasswordResetTokenByToken(token)
              .orElseThrow(() -> new PasswordResetTokenNotFoundException("token not found :" + token));

     LocalDateTime localDateTimeExperation =  passwordResetToken.getExpiryDate();

     if(localDateTimeExperation.isAfter(LocalDateTime.now()))
     {
         String passwordHashe = this.passwordEncoder.encode(password);
         passwordResetToken.getUser().setMotDePasse(passwordHashe);

         this.passwordResetTokenRepository.save(passwordResetToken);

     }
     else
     {
         throw new PasswordResetExpiredException("Token Password is Expired");
     }

    }
}

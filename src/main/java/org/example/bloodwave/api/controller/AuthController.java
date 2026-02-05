package org.example.bloodwave.api.controller;

import org.example.bloodwave.application.service.*;
import org.example.bloodwave.infrastructure.config.JwtUtil;
import org.example.bloodwave.application.dto.request.DemandeurDTO;
import org.example.bloodwave.application.dto.request.DonneurDTO;
import org.example.bloodwave.application.dto.request.HopitalDTO;
import org.example.bloodwave.application.dto.request.LoginRequest;
import org.example.bloodwave.application.dto.response.DemandeurDtoResponse;
import org.example.bloodwave.application.dto.response.DonneurDtoResponse;
import org.example.bloodwave.application.dto.response.HopitalDtoResponse;
import org.example.bloodwave.domain.entity.RefreshToken;
import org.example.bloodwave.domain.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final DonneurService donneurService;
    private final DemandeurService demandeurService;
    private final HopitalService hopitalService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final AuthService authService;

    @Autowired
    public AuthController(DonneurService donneurService, DemandeurService demandeurService, HopitalService hopitalService, AuthenticationManager authenticationManager, JwtUtil jwtUtil, RefreshTokenService refreshTokenService, AuthService authService)
    {
        this.donneurService = donneurService;
        this.demandeurService = demandeurService;
        this.hopitalService = hopitalService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.refreshTokenService = refreshTokenService;
        this.authService = authService;
    }

    @PostMapping("/register/donneur")
    public ResponseEntity<DonneurDtoResponse> registerDonneur(@RequestBody DonneurDTO dto)
    {
        return ResponseEntity.ok().body(this.donneurService.registerDonneur(dto));
    }

    @PostMapping("/register/demandeur")
    public ResponseEntity<DemandeurDtoResponse> registerDemandeur(@RequestBody DemandeurDTO dto)
    {
        return ResponseEntity.ok().body(this.demandeurService.registerDemandeur(dto));
    }

    @PostMapping("/register/hopital")
    public ResponseEntity<HopitalDtoResponse> registerHopital(@RequestBody HopitalDTO dto)
    {
        return ResponseEntity.ok().body(this.hopitalService.registerHopital(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest)
    {

        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword())
        );

        Utilisateur utilisateur =  this.authService.getUserByEmail(loginRequest.getEmail());
        String token =   this.jwtUtil.generateToken(utilisateur.getEmail(),utilisateur);
        RefreshToken refreshToken =  this.refreshTokenService.create(utilisateur.getEmail());

        Map<String, String> response = Map.of(
                "accessToken", token,
                "refreshToken", refreshToken.getToken()
        );

        return ResponseEntity.ok(response);

    }

    @PostMapping("/reset-password/{email}")
    public ResponseEntity<String> SendEmailResetPassword(@PathVariable("email") String email)
    {
        this.authService.resetPasswordByEmail(email);
        return ResponseEntity.ok().body("email reset password is sent");
    }

    @PutMapping("/reset-password/token/{token}")
    public ResponseEntity<String> resetPasswordByToken(@PathVariable("token") String token,@RequestBody String password)
    {
        this.authService.resetPasswordByToken(token,password);
        return ResponseEntity.ok().body("Your Password is reset success");
    }
}

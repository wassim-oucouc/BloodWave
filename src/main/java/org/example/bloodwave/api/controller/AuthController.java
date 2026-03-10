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

/**
 * REST Controller responsible for authentication and registration management.
 *
 * Base URL: /api/auth
 *
 * Handles:
 * - Registration (Donor, Requester, Hospital)
 * - Login (JWT Authentication)
 * - Refresh token generation
 * - Password reset functionality
 */
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
    public AuthController(
            DonneurService donneurService,
            DemandeurService demandeurService,
            HopitalService hopitalService,
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil,
            RefreshTokenService refreshTokenService,
            AuthService authService
    ) {
        this.donneurService = donneurService;
        this.demandeurService = demandeurService;
        this.hopitalService = hopitalService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.refreshTokenService = refreshTokenService;
        this.authService = authService;
    }

    /**
     * Registers a new donor.
     */
    @PostMapping("/register/donneur")
    public ResponseEntity<DonneurDtoResponse> registerDonneur(@RequestBody DonneurDTO dto) {
        return ResponseEntity.ok(donneurService.registerDonneur(dto));
    }

    /**
     * Registers a new requester.
     */
    @PostMapping("/register/demandeur")
    public ResponseEntity<DemandeurDtoResponse> registerDemandeur(@RequestBody DemandeurDTO dto) {
        return ResponseEntity.ok(demandeurService.registerDemandeur(dto));
    }

    /**
     * Registers a new hospital.
     */
    @PostMapping("/register/hopital")
    public ResponseEntity<HopitalDtoResponse> registerHopital(@RequestBody HopitalDTO dto) {
        return ResponseEntity.ok(hopitalService.registerHopital(dto));
    }

    /**
     * Authenticates user and generates access + refresh tokens.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("LOGIN CONTROLLER CALLED");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        Utilisateur utilisateur = authService.getUserByEmail(loginRequest.getEmail());

        String accessToken = jwtUtil.generateToken(utilisateur.getEmail(), utilisateur);
        RefreshToken refreshToken = refreshTokenService.create(utilisateur.getEmail());

        Map<String, String> response = Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken.getToken()
        );

        return ResponseEntity.ok(response);
    }

    /**
     * Sends password reset email.
     */
    @PostMapping("/reset-password/{email}")
    public ResponseEntity<String> sendEmailResetPassword(@PathVariable String email) {
        authService.resetPasswordByEmail(email);
        return ResponseEntity.ok("Reset password email sent successfully.");
    }

    /**
     * Resets password using reset token.
     */
    @PutMapping("/reset-password/token/{token}")
    public ResponseEntity<String> resetPasswordByToken(
            @PathVariable String token,
            @RequestBody String password
    ) {
        authService.resetPasswordByToken(token, password);
        return ResponseEntity.ok("Password reset successfully.");
    }
}
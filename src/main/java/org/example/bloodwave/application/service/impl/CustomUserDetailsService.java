package org.example.bloodwave.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.bloodwave.domain.entity.Utilisateur;
import org.example.bloodwave.domain.repository.UtilisateurRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

private UtilisateurRepository utilisateurRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Utilisateur utilisateur = (Utilisateur) this.utilisateurRepository.findByEmail(email).orElseThrow();

        Set<GrantedAuthority> authorities =
                Set.of(new SimpleGrantedAuthority(utilisateur.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                utilisateur.getEmail(),
                utilisateur.getMotDePasse(),
                authorities
        );

    }
}

package org.example.bloodwave.application.service.impl;

import org.example.bloodwave.application.dto.request.UtilisateurUpdateDTO;
import org.example.bloodwave.application.dto.response.DonneurDtoResponse;
import org.example.bloodwave.application.dto.response.UtilisateurDtoResponse;
import org.example.bloodwave.application.exceptions.PasswordNotMatchException;
import org.example.bloodwave.application.mapper.UtilisateurMapper;
import org.example.bloodwave.domain.entity.Utilisateur;
import org.example.bloodwave.domain.repository.UtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UtilisateurServiceImplTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private UtilisateurMapper utilisateurMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UtilisateurServiceImpl utilisateurService;

    @Test
    void updateUtilisateurById_shouldUpdateOnlyNonNullFields() {
        Utilisateur user = new Utilisateur() { };
        user.setId(1L);
        user.setNom("Ancien Nom");
        user.setTelephone("111");

        UtilisateurUpdateDTO dto = new UtilisateurUpdateDTO();
        dto.setNom("Nouveau Nom");
        dto.setVille("Paris");

        UtilisateurDtoResponse expected = new DonneurDtoResponse();

        when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(user));
        when(utilisateurRepository.save(user)).thenReturn(user);
        when(utilisateurMapper.toDtoResponse(user)).thenReturn(expected);

        UtilisateurDtoResponse result = utilisateurService.updateUtilisateurById(1L, dto);

        assertSame(expected, result);
        assertEquals("Nouveau Nom", user.getNom());
        assertEquals("Paris", user.getVille());
        assertEquals("111", user.getTelephone());
    }

    @Test
    void changePassword_shouldSaveEncodedPasswordWhenOldPasswordMatches() {
        Utilisateur user = new Utilisateur() { };
        user.setId(2L);
        user.setMotDePasse("encoded-old");

        when(utilisateurRepository.findById(2L)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("old-pass", "encoded-old")).thenReturn(true);
        when(passwordEncoder.encode("new-pass")).thenReturn("encoded-new");

        utilisateurService.changePassword(2L, "old-pass", "new-pass");

        assertEquals("encoded-new", user.getMotDePasse());
        verify(utilisateurRepository).save(user);
    }

    @Test
    void changePassword_shouldThrowWhenOldPasswordDoesNotMatch() {
        Utilisateur user = new Utilisateur() { };
        user.setId(3L);
        user.setMotDePasse("encoded-old");

        when(utilisateurRepository.findById(3L)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong-old", "encoded-old")).thenReturn(false);

        assertThrows(PasswordNotMatchException.class,
                () -> utilisateurService.changePassword(3L, "wrong-old", "new-pass"));

        verify(utilisateurRepository, never()).save(user);
    }

    @Test
    void banisseUtilisateurById_shouldSetActifFalse() {
        Utilisateur user = new Utilisateur() { };
        user.setId(4L);
        user.setActif(true);

        UtilisateurDtoResponse expected = new DonneurDtoResponse();

        when(utilisateurRepository.findById(4L)).thenReturn(Optional.of(user));
        when(utilisateurMapper.toDtoResponse(user)).thenReturn(expected);

        UtilisateurDtoResponse result = utilisateurService.banisseUtilisateurById(4L);

        assertSame(expected, result);
        assertEquals(false, user.getActif());
        verify(utilisateurRepository).save(user);
    }
}

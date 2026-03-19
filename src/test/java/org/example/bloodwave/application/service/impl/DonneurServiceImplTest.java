package org.example.bloodwave.application.service.impl;

import org.example.bloodwave.application.dto.request.DonneurDTO;
import org.example.bloodwave.application.dto.request.DonneurUpdateDTO;
import org.example.bloodwave.application.dto.response.DonneurDtoResponse;
import org.example.bloodwave.application.mapper.DonneurMapper;
import org.example.bloodwave.domain.entity.Donneur;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.example.bloodwave.domain.repository.DonneurRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DonneurServiceImplTest {

    @Mock
    private DonneurMapper donneurMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private DonneurRepository donneurRepository;

    @InjectMocks
    private DonneurServiceImpl donneurService;

    @Test
    void registerDonneur_shouldHashPasswordAndApplyDefaults() {
        DonneurDTO dto = new DonneurDTO();

        Donneur donneurToSave = new Donneur();
        donneurToSave.setMotDePasse("plain");
        donneurToSave.setNombreDonsTotaux(null);
        donneurToSave.setDisponible(null);

        Donneur saved = new Donneur();
        DonneurDtoResponse expected = new DonneurDtoResponse();

        when(donneurMapper.toEntity(dto)).thenReturn(donneurToSave);
        when(passwordEncoder.encode("plain")).thenReturn("hashed");
        when(donneurRepository.save(donneurToSave)).thenReturn(saved);
        when(donneurMapper.toDtoResponse(saved)).thenReturn(expected);

        DonneurDtoResponse result = donneurService.registerDonneur(dto);

        assertSame(expected, result);
        assertEquals("hashed", donneurToSave.getMotDePasse());
        assertEquals(0, donneurToSave.getNombreDonsTotaux());
        assertTrue(donneurToSave.getDisponible());
    }

    @Test
    void updateDonneurInfo_shouldUpdateOnlyProvidedFields() {
        Donneur existing = new Donneur();
        existing.setId(2L);
        existing.setWeight(55.0);
        existing.setDisponible(true);

        DonneurUpdateDTO dto = new DonneurUpdateDTO();
        dto.setPoids(62.5);
        dto.setGroupeSanguin(GroupeSanguin.A_POS);

        DonneurDtoResponse expected = new DonneurDtoResponse();

        when(donneurRepository.findById(2L)).thenReturn(Optional.of(existing));
        when(donneurRepository.save(existing)).thenReturn(existing);
        when(donneurMapper.toDtoResponse(existing)).thenReturn(expected);

        DonneurDtoResponse result = donneurService.updateDonneurInfo(2L, dto);

        assertSame(expected, result);
        assertEquals(62.5, existing.getWeight());
        assertEquals(GroupeSanguin.A_POS, existing.getGroupeSanguin());
        assertTrue(existing.getDisponible());
    }

    @Test
    void isEligible_shouldReturnFalseWhenLastDonationIsTooRecent() {
        Donneur donor = new Donneur();
        donor.setDateOfBirth(LocalDate.now().minusYears(30));
        donor.setWeight(70.0);
        donor.setLastDonationDate(LocalDate.now().minusWeeks(4));

        when(donneurRepository.findById(8L)).thenReturn(Optional.of(donor));

        boolean result = donneurService.isEligible(8L);

        assertFalse(result);
    }

    @Test
    void isEligible_shouldReturnTrueWhenAllConditionsAreMet() {
        Donneur donor = new Donneur();
        donor.setDateOfBirth(LocalDate.now().minusYears(28));
        donor.setWeight(75.0);
        donor.setLastDonationDate(LocalDate.now().minusWeeks(10));
        donor.setAMaladieChronique(false);
        donor.setEstSousTraitement(false);
        donor.setAInfectionRecente(false);
        donor.setASubiChirurgieRecente(false);
        donor.setEstEnceinte(false);

        when(donneurRepository.findById(9L)).thenReturn(Optional.of(donor));

        boolean result = donneurService.isEligible(9L);

        assertTrue(result);
    }
}

package org.example.bloodwave.application.service.impl;

import org.example.bloodwave.application.dto.request.DemandeurDTO;
import org.example.bloodwave.application.dto.response.DemandeurDtoResponse;
import org.example.bloodwave.application.mapper.DemandeurMapper;
import org.example.bloodwave.domain.entity.Demandeur;
import org.example.bloodwave.domain.enumeration.RoleType;
import org.example.bloodwave.domain.repository.DemandeurRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DemandeurServiceImplTest {

    @Mock
    private DemandeurRepository demandeurRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private DemandeurMapper demandeurMapper;

    @InjectMocks
    private DemandeurServiceImpl demandeurService;

    @Test
    void registerDemandeur_shouldHashPasswordAndSetRole() {
        DemandeurDTO dto = new DemandeurDTO();

        Demandeur demandeurToSave = new Demandeur();
        demandeurToSave.setMotDePasse("plain-password");

        Demandeur savedDemandeur = new Demandeur();
        DemandeurDtoResponse expectedResponse = new DemandeurDtoResponse();

        when(demandeurMapper.toEntity(dto)).thenReturn(demandeurToSave);
        when(passwordEncoder.encode("plain-password")).thenReturn("hashed-password");
        when(demandeurRepository.save(demandeurToSave)).thenReturn(savedDemandeur);
        when(demandeurMapper.toDtoResponse(savedDemandeur)).thenReturn(expectedResponse);

        DemandeurDtoResponse result = demandeurService.registerDemandeur(dto);

        assertSame(expectedResponse, result);
        assertEquals(RoleType.DEMANDEUR, demandeurToSave.getRole());
        assertEquals("hashed-password", demandeurToSave.getMotDePasse());

        verify(demandeurMapper).toEntity(dto);
        verify(passwordEncoder).encode("plain-password");
        verify(demandeurRepository).save(demandeurToSave);
    }
}

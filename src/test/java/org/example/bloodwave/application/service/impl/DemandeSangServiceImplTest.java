package org.example.bloodwave.application.service.impl;

import org.example.bloodwave.application.dto.request.DemandeSangDTO;
import org.example.bloodwave.application.dto.response.DemandeSangDtoResponse;
import org.example.bloodwave.application.exceptions.DemandeSangNotFoundException;
import org.example.bloodwave.application.mapper.DemandeSangMapper;
import org.example.bloodwave.application.service.EmailService;
import org.example.bloodwave.domain.entity.DemandeSang;
import org.example.bloodwave.domain.entity.Demandeur;
import org.example.bloodwave.domain.entity.Hopital;
import org.example.bloodwave.domain.enumeration.StatutDemande;
import org.example.bloodwave.domain.repository.DemandeSangRepository;
import org.example.bloodwave.domain.repository.DemandeurRepository;
import org.example.bloodwave.domain.repository.HopitalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DemandeSangServiceImplTest {

    @Mock
    private DemandeSangRepository demandeSangRepository;

    @Mock
    private DemandeurRepository demandeurRepository;

    @Mock
    private HopitalRepository hopitalRepository;

    @Mock
    private DemandeSangMapper demandeSangMapper;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private DemandeSangServiceImpl demandeSangService;

    @Test
    void create_shouldSetRelationsStatusAndCreationDate() {
        DemandeSangDTO dto = new DemandeSangDTO();
        dto.setDemandeurId(1L);
        dto.setHopitalId(2L);

        Demandeur demandeur = new Demandeur();
        Hopital hopital = new Hopital();
        DemandeSang demande = new DemandeSang();
        DemandeSang saved = new DemandeSang();
        DemandeSangDtoResponse expected = new DemandeSangDtoResponse();

        when(demandeurRepository.findById(1L)).thenReturn(Optional.of(demandeur));
        when(hopitalRepository.findById(2L)).thenReturn(Optional.of(hopital));
        when(demandeSangMapper.toEntity(dto)).thenReturn(demande);
        when(demandeSangRepository.save(demande)).thenReturn(saved);
        when(demandeSangMapper.toDtoResponse(saved)).thenReturn(expected);

        DemandeSangDtoResponse result = demandeSangService.create(dto);

        assertSame(expected, result);
        assertSame(demandeur, demande.getDemandeur());
        assertSame(hopital, demande.getHopital());
        assertEquals(StatutDemande.EN_ATTENTE, demande.getStatut());
        assertNotNull(demande.getDateCreation());
    }

    @Test
    void getById_shouldThrowWhenNotFound() {
        when(demandeSangRepository.findById(88L)).thenReturn(Optional.empty());

        assertThrows(DemandeSangNotFoundException.class, () -> demandeSangService.getById(88L));
    }

    @Test
    void approveDemande_shouldSetApprovedAndSendEmail() {
        Demandeur demandeur = new Demandeur();
        demandeur.setEmail("d@example.com");
        demandeur.setNom("Ali");

        DemandeSang demande = new DemandeSang();
        demande.setId(10L);
        demande.setDemandeur(demandeur);
        demande.setStatut(StatutDemande.EN_ATTENTE);

        when(demandeSangRepository.findById(10L)).thenReturn(Optional.of(demande));

        demandeSangService.approveDemande(10L);

        assertEquals(StatutDemande.ACCEPTEE, demande.getStatut());
        verify(emailService).sendEmail(
                org.mockito.ArgumentMatchers.eq("d@example.com"),
                org.mockito.ArgumentMatchers.contains("Approved"),
                org.mockito.ArgumentMatchers.contains("APPROVED")
        );
        verify(demandeSangRepository).save(demande);
    }

    @Test
    void rejectDemande_shouldSetRejectedAndSendEmail() {
        Demandeur demandeur = new Demandeur();
        demandeur.setEmail("d@example.com");
        demandeur.setNom("Ali");

        DemandeSang demande = new DemandeSang();
        demande.setId(11L);
        demande.setDemandeur(demandeur);
        demande.setStatut(StatutDemande.EN_ATTENTE);

        when(demandeSangRepository.findById(11L)).thenReturn(Optional.of(demande));

        demandeSangService.rejectDemande(11L);

        assertEquals(StatutDemande.REFUSEE, demande.getStatut());
        verify(emailService).sendEmail(
                org.mockito.ArgumentMatchers.eq("d@example.com"),
                org.mockito.ArgumentMatchers.contains("Refused"),
                org.mockito.ArgumentMatchers.contains("REFUSED")
        );
        verify(demandeSangRepository).save(demande);
    }
}

package org.example.bloodwave.application.service.impl;

import org.example.bloodwave.application.dto.request.CollecteSangDTO;
import org.example.bloodwave.application.dto.response.CollecteSangDtoResponse;
import org.example.bloodwave.application.exceptions.HopitalNotFoundException;
import org.example.bloodwave.application.mapper.CollecteSangMapper;
import org.example.bloodwave.domain.entity.CollecteSang;
import org.example.bloodwave.domain.entity.Donneur;
import org.example.bloodwave.domain.entity.Hopital;
import org.example.bloodwave.domain.entity.InscriptionCollecte;
import org.example.bloodwave.domain.enumeration.StatutCollecte;
import org.example.bloodwave.domain.repository.CollecteSangRepository;
import org.example.bloodwave.domain.repository.HopitalRepository;
import org.example.bloodwave.domain.repository.InscriptionCollecteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CollectSangServiceImplTest {

    @Mock
    private CollecteSangRepository collecteSangRepository;

    @Mock
    private HopitalRepository hopitalRepository;

    @Mock
    private CollecteSangMapper collecteSangMapper;

    @Mock
    private InscriptionCollecteRepository inscriptionCollecteRepository;

    @InjectMocks
    private CollectSangServiceImpl collectSangService;

    @Test
    void createCollecte_shouldSetHopitalAndDefaultStatus() {
        CollecteSangDTO dto = new CollecteSangDTO();
        dto.setHopitalId(1L);
        dto.setDescription("Collecte du weekend");
        dto.setDateCollecte(LocalDateTime.now().plusDays(3));

        Hopital hopital = new Hopital();
        hopital.setId(1L);

        CollecteSang collecteToSave = new CollecteSang();
        CollecteSang savedCollecte = new CollecteSang();
        CollecteSangDtoResponse expectedResponse = new CollecteSangDtoResponse();

        when(hopitalRepository.findById(1L)).thenReturn(Optional.of(hopital));
        when(collecteSangMapper.toEntity(dto)).thenReturn(collecteToSave);
        when(collecteSangRepository.save(collecteToSave)).thenReturn(savedCollecte);
        when(collecteSangMapper.toDtoResponse(savedCollecte)).thenReturn(expectedResponse);

        CollecteSangDtoResponse result = collectSangService.createCollecte(dto);

        assertSame(expectedResponse, result);
        assertSame(hopital, collecteToSave.getHopital());
        assertEquals(StatutCollecte.PLANIFIEE, collecteToSave.getStatut());
        assertEquals("Collecte du weekend", collecteToSave.getDescription());
        verify(collecteSangRepository).save(collecteToSave);
    }

    @Test
    void createCollecte_shouldThrowWhenHopitalDoesNotExist() {
        CollecteSangDTO dto = new CollecteSangDTO();
        dto.setHopitalId(99L);

        when(hopitalRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(HopitalNotFoundException.class, () -> collectSangService.createCollecte(dto));
        verify(collecteSangMapper, never()).toEntity(dto);
        verify(collecteSangRepository, never()).save(org.mockito.ArgumentMatchers.any());
    }

    @Test
    void cancelParticipation_shouldMarkInscriptionAsNotJoined() {
        Long collecteId = 10L;
        Long donneurId = 5L;

        Donneur donneur = new Donneur();
        donneur.setId(donneurId);

        CollecteSang collecte = new CollecteSang();
        collecte.setInscriptions(new ArrayList<>());

        InscriptionCollecte inscription = new InscriptionCollecte();
        inscription.setJoined(true);

        CollecteSangDtoResponse expectedResponse = new CollecteSangDtoResponse();

        when(collecteSangRepository.findById(collecteId)).thenReturn(Optional.of(collecte));
        when(inscriptionCollecteRepository.findByDonneurId(donneurId)).thenReturn(inscription);
        when(collecteSangRepository.save(collecte)).thenReturn(collecte);
        when(collecteSangMapper.toDtoResponse(collecte)).thenReturn(expectedResponse);

        CollecteSangDtoResponse result = collectSangService.cancelParticipation(collecteId, donneur);

        assertSame(expectedResponse, result);
        assertFalse(inscription.isJoined());
        verify(inscriptionCollecteRepository).save(inscription);
        verify(collecteSangRepository).save(collecte);
    }
}

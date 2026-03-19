package org.example.bloodwave.application.service.impl;

import org.example.bloodwave.application.dto.request.DonDTO;
import org.example.bloodwave.application.dto.response.DonDtoResponse;
import org.example.bloodwave.application.mapper.DonMapper;
import org.example.bloodwave.application.service.DonneurService;
import org.example.bloodwave.application.service.StockSangService;
import org.example.bloodwave.domain.entity.Don;
import org.example.bloodwave.domain.entity.Donneur;
import org.example.bloodwave.domain.entity.Hopital;
import org.example.bloodwave.domain.entity.StockSang;
import org.example.bloodwave.domain.entity.UniteSang;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.example.bloodwave.domain.enumeration.StatutDon;
import org.example.bloodwave.domain.repository.DonRepository;
import org.example.bloodwave.domain.repository.DonneurRepository;
import org.example.bloodwave.domain.repository.HopitalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DonServiceImplTest {

    @Mock
    private DonRepository donRepository;

    @Mock
    private DonneurRepository donneurRepository;

    @Mock
    private HopitalRepository hopitalRepository;

    @Mock
    private DonMapper donMapper;

    @Mock
    private DonneurService donneurService;

    @Mock
    private StockSangService stockSangService;

    @InjectMocks
    private DonServiceImpl donService;

    @Test
    void createDonation_shouldThrowWhenDonorIsNotEligible() {
        DonDTO dto = new DonDTO();
        dto.setDonneurId(1L);
        dto.setHopitalId(2L);

        Donneur donneur = new Donneur();
        donneur.setId(1L);

        Hopital hopital = new Hopital();
        hopital.setId(2L);

        when(donneurRepository.findById(1L)).thenReturn(Optional.of(donneur));
        when(hopitalRepository.findById(2L)).thenReturn(Optional.of(hopital));
        when(donneurService.isEligible(1L)).thenReturn(false);

        assertThrows(IllegalStateException.class, () -> donService.createDonation(dto));
        verify(donRepository, never()).save(any());
    }

    @Test
    void createDonation_shouldSetStatusAndLinksWhenEligible() {
        DonDTO dto = new DonDTO();
        dto.setDonneurId(1L);
        dto.setHopitalId(2L);

        Donneur donneur = new Donneur();
        donneur.setId(1L);

        Hopital hopital = new Hopital();
        hopital.setId(2L);

        Don donToSave = new Don();
        Don savedDon = new Don();
        DonDtoResponse expectedResponse = new DonDtoResponse();

        when(donneurRepository.findById(1L)).thenReturn(Optional.of(donneur));
        when(hopitalRepository.findById(2L)).thenReturn(Optional.of(hopital));
        when(donneurService.isEligible(1L)).thenReturn(true);
        when(donMapper.toEntity(dto)).thenReturn(donToSave);
        when(donRepository.save(donToSave)).thenReturn(savedDon);
        when(donMapper.toDtoResponse(savedDon)).thenReturn(expectedResponse);

        DonDtoResponse result = donService.createDonation(dto);

        assertSame(expectedResponse, result);
        assertEquals(StatutDon.PLANIFIE, donToSave.getStatut());
        assertSame(donneur, donToSave.getDonneur());
        assertSame(hopital, donToSave.getHopital());
    }

    @Test
    void approveDonation_shouldConfirmDonationAndAddStockUnit() {
        Long donId = 10L;

        Donneur donneur = new Donneur();
        donneur.setId(1L);
        donneur.setGroupeSanguin(GroupeSanguin.O_POS);
        donneur.setNombreDonsTotaux(2);

        Don don = new Don();
        don.setId(donId);
        don.setDonneur(donneur);
        don.setQuantite(450.0);

        StockSang stock = new StockSang();
        DonDtoResponse expectedResponse = new DonDtoResponse();

        when(donRepository.findById(donId)).thenReturn(Optional.of(don));
        when(donneurRepository.findById(1L)).thenReturn(Optional.of(donneur));
        when(donRepository.save(don)).thenReturn(don);
        when(stockSangService.getStockSangByGroupeSang(GroupeSanguin.O_POS)).thenReturn(stock);
        when(donMapper.toDtoResponse(don)).thenReturn(expectedResponse);

        DonDtoResponse result = donService.approveDonation(donId);

        assertSame(expectedResponse, result);
        assertEquals(StatutDon.CONFIRME, don.getStatut());
        assertEquals(3, donneur.getNombreDonsTotaux());
        assertNotNull(donneur.getLastDonationDate());

        ArgumentCaptor<UniteSang> uniteCaptor = ArgumentCaptor.forClass(UniteSang.class);
        verify(stockSangService).ajouterAuStock(eq(stock), eq(450), uniteCaptor.capture());
        UniteSang uniteCreee = uniteCaptor.getValue();
        assertSame(don, uniteCreee.getDon());
        assertSame(stock, uniteCreee.getStockSang());
        assertNotNull(uniteCreee.getDatePrelevement());
        assertNotNull(uniteCreee.getDateExpiration());
    }

    @Test
    void cancelDonById_shouldSetStatusToAnnule() {
        Long donId = 15L;

        Don don = new Don();
        don.setId(donId);
        don.setStatut(StatutDon.PLANIFIE);

        DonDtoResponse expectedResponse = new DonDtoResponse();

        when(donRepository.findById(donId)).thenReturn(Optional.of(don));
        when(donRepository.save(don)).thenReturn(don);
        when(donMapper.toDtoResponse(don)).thenReturn(expectedResponse);

        DonDtoResponse result = donService.cancelDonById(donId);

        assertSame(expectedResponse, result);
        assertEquals(StatutDon.ANNULE, don.getStatut());
    }
}

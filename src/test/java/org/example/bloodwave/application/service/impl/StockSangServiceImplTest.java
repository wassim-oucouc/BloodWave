package org.example.bloodwave.application.service.impl;

import org.example.bloodwave.application.dto.request.StockSangDTO;
import org.example.bloodwave.application.dto.response.StockSangDtoResponse;
import org.example.bloodwave.application.exceptions.HopitalNotFoundException;
import org.example.bloodwave.application.mapper.StockSangMapper;
import org.example.bloodwave.application.mapper.UnitSangMapper;
import org.example.bloodwave.domain.entity.Hopital;
import org.example.bloodwave.domain.entity.MouvementStock;
import org.example.bloodwave.domain.entity.StockSang;
import org.example.bloodwave.domain.entity.UniteSang;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.example.bloodwave.domain.enumeration.TypeMouvement;
import org.example.bloodwave.domain.repository.HopitalRepository;
import org.example.bloodwave.domain.repository.MouvementStockRepository;
import org.example.bloodwave.domain.repository.StockSangRepository;
import org.example.bloodwave.domain.repository.UnitSangRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockSangServiceImplTest {

    @Mock
    private StockSangRepository stockSangRepository;

    @Mock
    private MouvementStockRepository mouvementStockRepository;

    @Mock
    private HopitalRepository hopitalRepository;

    @Mock
    private UnitSangRepository unitSangRepository;

    @Mock
    private StockSangMapper stockSangMapper;

    @Mock
    private UnitSangMapper unitSangMapper;

    @InjectMocks
    private StockSangServiceImpl stockSangService;

    @Test
    void createStock_shouldSetDefaultQuantityWhenNull() {
        StockSangDTO dto = new StockSangDTO();
        dto.setHopitalId(10L);

        Hopital hopital = new Hopital();
        hopital.setId(10L);

        StockSang stockToSave = new StockSang();
        stockToSave.setQuantiteDisponible(null);

        StockSang savedStock = new StockSang();
        StockSangDtoResponse expectedResponse = new StockSangDtoResponse();

        when(hopitalRepository.findById(10L)).thenReturn(Optional.of(hopital));
        when(stockSangMapper.toEntity(dto)).thenReturn(stockToSave);
        when(stockSangRepository.save(stockToSave)).thenReturn(savedStock);
        when(stockSangMapper.toDtoResponse(savedStock)).thenReturn(expectedResponse);

        StockSangDtoResponse result = stockSangService.createStock(dto);

        assertSame(expectedResponse, result);
        assertEquals(0, stockToSave.getQuantiteDisponible());
        assertSame(hopital, stockToSave.getHopital());
    }

    @Test
    void updateStock_shouldUpdateOnlyProvidedFields() {
        Long stockId = 5L;

        StockSang existing = new StockSang();
        existing.setId(stockId);
        existing.setGroupeSanguin(GroupeSanguin.A_POS);
        existing.setQuantiteDisponible(3);
        existing.setSeuilAlerte(2);

        StockSangDTO dto = new StockSangDTO();
        dto.setQuantiteDisponible(12);

        StockSangDtoResponse expectedResponse = new StockSangDtoResponse();

        when(stockSangRepository.findById(stockId)).thenReturn(Optional.of(existing));
        when(stockSangRepository.save(existing)).thenReturn(existing);
        when(stockSangMapper.toDtoResponse(existing)).thenReturn(expectedResponse);

        StockSangDtoResponse result = stockSangService.updateStock(stockId, dto);

        assertSame(expectedResponse, result);
        assertEquals(12, existing.getQuantiteDisponible());
        assertEquals(GroupeSanguin.A_POS, existing.getGroupeSanguin());
        assertEquals(2, existing.getSeuilAlerte());
    }

    @Test
    void updateStock_shouldThrowWhenHopitalInDtoDoesNotExist() {
        Long stockId = 7L;

        StockSang existing = new StockSang();
        StockSangDTO dto = new StockSangDTO();
        dto.setHopitalId(88L);

        when(stockSangRepository.findById(stockId)).thenReturn(Optional.of(existing));
        when(hopitalRepository.findById(88L)).thenReturn(Optional.empty());

        assertThrows(HopitalNotFoundException.class, () -> stockSangService.updateStock(stockId, dto));
    }

    @Test
    void deleteStock_shouldDetachUnitsBeforeDeletion() {
        Long stockId = 21L;

        StockSang stock = new StockSang();
        stock.setId(stockId);

        UniteSang u1 = new UniteSang();
        u1.setStockSang(stock);
        UniteSang u2 = new UniteSang();
        u2.setStockSang(stock);

        when(stockSangRepository.findById(stockId)).thenReturn(Optional.of(stock));
        when(unitSangRepository.findByStockSangId(stockId)).thenReturn(List.of(u1, u2));

        stockSangService.deleteStock(stockId);

        assertNull(u1.getStockSang());
        assertNull(u2.getStockSang());
        verify(unitSangRepository).saveAll(List.of(u1, u2));
        verify(stockSangRepository).delete(stock);
    }

    @Test
    void ajouterAuStock_shouldIncreaseQuantityAndCreateEntryMovement() {
        StockSang stock = new StockSang();
        stock.setQuantiteDisponible(4);

        UniteSang unite = new UniteSang();

        stockSangService.ajouterAuStock(stock, 2, unite);

        assertEquals(6, stock.getQuantiteDisponible());
        verify(stockSangRepository).save(stock);

        ArgumentCaptor<MouvementStock> movementCaptor = ArgumentCaptor.forClass(MouvementStock.class);
        verify(mouvementStockRepository).save(movementCaptor.capture());

        MouvementStock movement = movementCaptor.getValue();
        assertEquals(TypeMouvement.ENTREE, movement.getType());
        assertEquals(2, movement.getQuantite());
        assertSame(unite, movement.getUniteSang());
        assertSame(stock, movement.getStockSang());
    }

    @Test
    void retirerDuStock_shouldThrowWhenQuantityIsInsufficient() {
        StockSang stock = new StockSang();
        stock.setQuantiteDisponible(1);

        UniteSang unite = new UniteSang();

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> stockSangService.retirerDuStock(stock, 3, unite, "sortie"));

        assertEquals("Stock insuffisant pour ce groupe", ex.getMessage());
    }
}

package org.example.bloodwave.application.service.impl;

import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.request.StockSangDTO;
import org.example.bloodwave.application.dto.response.StockSangDtoResponse;
import org.example.bloodwave.application.dto.response.UniteSangDtoResponse;
import org.example.bloodwave.application.exceptions.HopitalNotFoundException;
import org.example.bloodwave.application.exceptions.StockSangNotFoundException;
import org.example.bloodwave.application.mapper.StockSangMapper;
import org.example.bloodwave.application.mapper.UnitSangMapper;
import org.example.bloodwave.application.service.StockSangService;
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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
public class StockSangServiceImpl implements StockSangService {


    private StockSangRepository stockSangRepository;
    private MouvementStockRepository mouvementStockRepository;
    private HopitalRepository hopitalRepository;
    private UnitSangRepository unitSangRepository;
    private StockSangMapper stockSangMapper;
    private UnitSangMapper unitSangMapper;

    public StockSangDtoResponse createStock(StockSangDTO dto) {
        Hopital hopital = hopitalRepository.findById(dto.getHopitalId())
                .orElseThrow(() -> new HopitalNotFoundException("hopital not found with id " + dto.getHopitalId()));

        StockSang stock = stockSangMapper.toEntity(dto);
        stock.setHopital(hopital);
        if (stock.getQuantiteDisponible() == null) {
            stock.setQuantiteDisponible(0);
        }

        StockSang created = stockSangRepository.save(stock);
        return stockSangMapper.toDtoResponse(created);
    }

    public StockSangDtoResponse updateStock(Long stockId, StockSangDTO dto) {
        StockSang stock = stockSangRepository.findById(stockId)
                .orElseThrow(() -> new StockSangNotFoundException("stock not found with id " + stockId));

        if (dto.getGroupeSanguin() != null) {
            stock.setGroupeSanguin(dto.getGroupeSanguin());
        }
        if (dto.getQuantiteDisponible() != null) {
            stock.setQuantiteDisponible(dto.getQuantiteDisponible());
        }
        if (dto.getSeuilAlerte() != null) {
            stock.setSeuilAlerte(dto.getSeuilAlerte());
        }
        if (dto.getHopitalId() != null) {
            Hopital hopital = hopitalRepository.findById(dto.getHopitalId())
                    .orElseThrow(() -> new HopitalNotFoundException("hopital not found with id " + dto.getHopitalId()));
            stock.setHopital(hopital);
        }

        StockSang updated = stockSangRepository.save(stock);
        return stockSangMapper.toDtoResponse(updated);
    }

    public void deleteStock(Long stockId) {
        StockSang stock = stockSangRepository.findById(stockId)
                .orElseThrow(() -> new StockSangNotFoundException("stock not found with id " + stockId));

        // Keep unit history while detaching them from the removed stock.
        List<UniteSang> unites = unitSangRepository.findByStockSangId(stockId);
        for (UniteSang unite : unites) {
            unite.setStockSang(null);
        }
        unitSangRepository.saveAll(unites);

        stockSangRepository.delete(stock);
    }

    public StockSangDtoResponse getStockById(Long stockId) {
        StockSang stock = stockSangRepository.findById(stockId)
                .orElseThrow(() -> new StockSangNotFoundException("stock not found with id " + stockId));
        return stockSangMapper.toDtoResponse(stock);
    }

    public List<StockSangDtoResponse> getAllStocksDto() {
        return stockSangRepository.findAll()
                .stream()
                .map(stockSangMapper::toDtoResponse)
                .toList();
    }

    public List<UniteSangDtoResponse> getUnitesByStockId(Long stockId) {
        stockSangRepository.findById(stockId)
                .orElseThrow(() -> new StockSangNotFoundException("stock not found with id " + stockId));

        return unitSangRepository.findByStockSangId(stockId)
                .stream()
                .map(unitSangMapper::toDtoResponse)
                .toList();
    }

    public StockSang getStockByHopitalAndGroupe(Hopital hopital, GroupeSanguin groupe) {
        return stockSangRepository.findByHopitalAndGroupeSanguin(hopital, groupe)
                .orElseThrow(() -> new RuntimeException("Stock non trouvé pour ce groupe"));
    }

    public List<StockSang> getAllStocks()
    {
        return this.stockSangRepository.findAll();
    }

    public void ajouterAuStock(StockSang stock, int quantite, UniteSang unite) {
        int quantiteActuelle = stock.getQuantiteDisponible() == null ? 0 : stock.getQuantiteDisponible();
        stock.setQuantiteDisponible(quantiteActuelle + quantite);
        stockSangRepository.save(stock);

        UniteSang uniteSaved = null;
        if (unite != null) {
            if (unite.getStockSang() == null) {
                unite.setStockSang(stock);
            }
            uniteSaved = unitSangRepository.save(unite);
        }

        MouvementStock m = new MouvementStock();
        m.setStockSang(stock);
        m.setType(TypeMouvement.ENTREE);
        m.setQuantite(quantite);
        m.setUniteSang(uniteSaved);
        m.setDate(LocalDateTime.now());
        mouvementStockRepository.save(m);
    }

    public void retirerDuStock(StockSang stock, int quantite, UniteSang unite, String commentaire) {
        int disponible = stock.getQuantiteDisponible();
        if (disponible < quantite) {
            throw new RuntimeException("Stock insuffisant pour ce groupe");
        }

        stock.setQuantiteDisponible(disponible - quantite);
        stockSangRepository.save(stock);

        MouvementStock m = new MouvementStock();
        m.setStockSang(stock);
        m.setType(TypeMouvement.SORTIE);
        m.setQuantite(quantite);
        m.setUniteSang(unite);
        m.setDate(LocalDateTime.now());
        mouvementStockRepository.save(m);
    }

    public StockSang getStockSangByGroupeSang(GroupeSanguin groupeSanguin)
    {
        return this.stockSangRepository.findStockSangByGroupeSanguin(groupeSanguin).orElseThrow(() ->new StockSangNotFoundException("Stock Sang Not Found with groupeSanguin : " +  groupeSanguin));
    }

    public List<StockSangDtoResponse> getStocksByHopitalId(Long hopitalId) {
        // Ne pas lancer d'exception si l'hôpital n'existe pas, retourner une liste vide
        List<StockSang> stocks = stockSangRepository.findByHopital_Id(hopitalId);
        return stocks.stream()
                .map(stockSangMapper::toDtoResponse)
                .toList();
    }
}

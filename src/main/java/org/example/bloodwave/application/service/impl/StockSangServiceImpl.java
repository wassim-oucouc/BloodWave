package org.example.bloodwave.application.service.impl;

import lombok.AllArgsConstructor;
import org.example.bloodwave.application.service.StockSangService;
import org.example.bloodwave.domain.entity.Hopital;
import org.example.bloodwave.domain.entity.MouvementStock;
import org.example.bloodwave.domain.entity.StockSang;
import org.example.bloodwave.domain.entity.UniteSang;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.example.bloodwave.domain.enumeration.TypeMouvement;
import org.example.bloodwave.domain.repository.MouvementStockRepository;
import org.example.bloodwave.domain.repository.StockSangRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
public class StockSangServiceImpl implements StockSangService {


    private StockSangRepository stockSangRepository;
    private MouvementStockRepository mouvementStockRepository;

    public StockSang getStockByHopitalAndGroupe(Hopital hopital, GroupeSanguin groupe) {
        return stockSangRepository.findByHopitalAndGroupeSanguin(hopital, groupe)
                .orElseThrow(() -> new RuntimeException("Stock non trouvé pour ce groupe"));
    }

    public List<StockSang> getAllStocks()
    {
        return this.stockSangRepository.findAll();
    }

    public void ajouterAuStock(StockSang stock, int quantite, UniteSang unite) {
        stock.setQuantiteDisponible(stock.getQuantiteDisponible() + quantite);
        stockSangRepository.save(stock);

        MouvementStock m = new MouvementStock();
        m.setStockSang(stock);
        m.setType(TypeMouvement.ENTREE);
        m.setQuantite(quantite);
        m.setUniteSang(unite);
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
}

package org.example.bloodwave.application.service;


import org.example.bloodwave.domain.entity.Hopital;
import org.example.bloodwave.domain.entity.StockSang;
import org.example.bloodwave.domain.entity.UniteSang;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StockSangService {

    public StockSang getStockByHopitalAndGroupe(Hopital hopital, GroupeSanguin groupe);

    public void ajouterAuStock(StockSang stock, int quantite, UniteSang unite);

    public void retirerDuStock(StockSang stock, int quantite, UniteSang unite, String commentaire);

    public List<StockSang> getAllStocks();

    public StockSang getStockSangByGroupeSang(GroupeSanguin groupeSanguin);


}

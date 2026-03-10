package org.example.bloodwave.application.service.impl;

import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.response.AdminAnalyticsResponse;
import org.example.bloodwave.application.service.AnalyticsService;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.example.bloodwave.domain.enumeration.StatutDemande;
import org.example.bloodwave.domain.enumeration.StatutDon;
import org.example.bloodwave.domain.repository.DemandeSangRepository;
import org.example.bloodwave.domain.repository.DonRepository;
import org.example.bloodwave.domain.repository.DonneurRepository;
import org.example.bloodwave.domain.repository.StockSangRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final DonRepository donRepository;
    private final DemandeSangRepository demandeSangRepository;
    private final DonneurRepository donneurRepository;
    private final StockSangRepository stockSangRepository;

    @Override
    public AdminAnalyticsResponse getGlobalAnalytics() {

        AdminAnalyticsResponse response = new AdminAnalyticsResponse();

        response.setStatsDons(buildStatsDons());
        response.setStatsDemandes(buildStatsDemandes());
        response.setStatsDonneurs(buildStatsDonneurs());
        response.setStatsStock(buildStatsStock());

        return response;
    }

    private AdminAnalyticsResponse.StatsDons buildStatsDons() {

        AdminAnalyticsResponse.StatsDons stats = new AdminAnalyticsResponse.StatsDons();

        stats.setTotal(donRepository.count());
        stats.setPlanifie(donRepository.countByStatut(StatutDon.PLANIFIE));
        stats.setConfirme(donRepository.countByStatut(StatutDon.CONFIRME));
        stats.setAnnule(donRepository.countByStatut(StatutDon.ANNULE));
        stats.setTotalQuantite(donRepository.sumQuantiteTotale());

        return stats;
    }

    private AdminAnalyticsResponse.StatsDemandes buildStatsDemandes() {

        AdminAnalyticsResponse.StatsDemandes stats = new AdminAnalyticsResponse.StatsDemandes();

        stats.setTotal(demandeSangRepository.count());
        stats.setEnAttente(demandeSangRepository.countByStatut(StatutDemande.EN_ATTENTE));
        stats.setAcceptee(demandeSangRepository.countByStatut(StatutDemande.ACCEPTEE));
        stats.setRefusee(demandeSangRepository.countByStatut(StatutDemande.REFUSEE));

        Map<String, Long> parGroupe = new LinkedHashMap<>();
        for (GroupeSanguin groupe : GroupeSanguin.values()) {
            parGroupe.put(groupe.name(), demandeSangRepository.countByGroupeSanguin(groupe));
        }
        stats.setParGroupeSanguin(parGroupe);

        return stats;
    }

    private AdminAnalyticsResponse.StatsDonneurs buildStatsDonneurs() {

        AdminAnalyticsResponse.StatsDonneurs stats = new AdminAnalyticsResponse.StatsDonneurs();

        stats.setTotal(donneurRepository.count());
        stats.setDisponibles(donneurRepository.countByDisponibleTrue());

        Map<String, Long> parGroupe = new LinkedHashMap<>();
        for (GroupeSanguin groupe : GroupeSanguin.values()) {
            parGroupe.put(groupe.name(), donneurRepository.countByGroupeSanguin(groupe));
        }
        stats.setParGroupeSanguin(parGroupe);

        return stats;
    }

    private AdminAnalyticsResponse.StatsStock buildStatsStock() {

        AdminAnalyticsResponse.StatsStock stats = new AdminAnalyticsResponse.StatsStock();

        stats.setTotalUnitesDansLeSysteme(stockSangRepository.sumQuantiteTotale());

        Map<String, Integer> parGroupe = new LinkedHashMap<>();
        for (GroupeSanguin groupe : GroupeSanguin.values()) {
            parGroupe.put(groupe.name(), stockSangRepository.sumQuantiteByGroupe(groupe));
        }
        stats.setParGroupeSanguin(parGroupe);

        stats.setNombreStocksCritiques(stockSangRepository.findStocksCritiques().size());

        return stats;
    }
}

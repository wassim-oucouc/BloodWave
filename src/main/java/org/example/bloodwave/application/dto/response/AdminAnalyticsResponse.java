package org.example.bloodwave.application.dto.response;

import lombok.Data;

import java.util.Map;

@Data
public class AdminAnalyticsResponse {

    private StatsDons statsDons;

    private StatsDemandes statsDemandes;

    private StatsDonneurs statsDonneurs;

    private StatsStock statsStock;

    @Data
    public static class StatsDons {
        private long total;
        private long planifie;
        private long confirme;
        private long annule;
        private double totalQuantite;
    }

    @Data
    public static class StatsDemandes {
        private long total;
        private long enAttente;
        private long acceptee;
        private long refusee;
        private Map<String, Long> parGroupeSanguin;
    }

    @Data
    public static class StatsDonneurs {
        private long total;
        private long disponibles;
        private Map<String, Long> parGroupeSanguin;
    }

    @Data
    public static class StatsStock {
        private int totalUnitesDansLeSysteme;
        private Map<String, Integer> parGroupeSanguin;
        private int nombreStocksCritiques;
    }
}

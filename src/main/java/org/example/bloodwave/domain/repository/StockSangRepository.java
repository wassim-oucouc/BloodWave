package org.example.bloodwave.domain.repository;

import org.example.bloodwave.domain.entity.Hopital;
import org.example.bloodwave.domain.entity.StockSang;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockSangRepository extends JpaRepository<StockSang, Long> {
    Optional<StockSang> findByHopitalAndGroupeSanguin(Hopital hopital, GroupeSanguin groupe);

    Optional<StockSang> findStockSangByGroupeSanguin(GroupeSanguin groupeSanguin);

    List<StockSang> findByHopital(Hopital hopital);

    @Query("SELECT COALESCE(SUM(s.quantiteDisponible), 0) FROM StockSang s")
    int sumQuantiteTotale();

    @Query("SELECT COALESCE(SUM(s.quantiteDisponible), 0) FROM StockSang s WHERE s.groupeSanguin = :groupe")
    int sumQuantiteByGroupe(GroupeSanguin groupe);

    @Query("SELECT s FROM StockSang s WHERE s.quantiteDisponible <= s.seuilAlerte")
    List<StockSang> findStocksCritiques();
}

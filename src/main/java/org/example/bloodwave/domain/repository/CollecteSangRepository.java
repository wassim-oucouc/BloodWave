package org.example.bloodwave.domain.repository;


import org.example.bloodwave.domain.entity.CollecteSang;
import org.example.bloodwave.domain.entity.Hopital;
import org.example.bloodwave.domain.enumeration.StatutCollecte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Repository
public interface CollecteSangRepository extends JpaRepository<CollecteSang,Long> {
    @Query("SELECT DISTINCT c FROM CollecteSang c " +
            "JOIN FETCH c.hopital " +
            "LEFT JOIN FETCH c.inscriptions")
    List<CollecteSang> findAllWithHopitalAndInscriptions();

    List<CollecteSang> findByStatut(StatutCollecte statut);

    boolean existsByHopitalAndDateCollecte(Hopital hopital, LocalDateTime dateCollecte);

    List<CollecteSang> findByHopitalAndDateCollecteBetween(Hopital hopital, LocalDateTime start, LocalDateTime end);
}

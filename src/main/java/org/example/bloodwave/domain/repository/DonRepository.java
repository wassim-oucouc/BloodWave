package org.example.bloodwave.domain.repository;

import org.example.bloodwave.domain.entity.Don;
import org.example.bloodwave.domain.entity.Donneur;
import org.example.bloodwave.domain.enumeration.StatutDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonRepository extends JpaRepository<Don, Long> {
    List<Don> getDonsByDonneur(Donneur donneur);

    List<Don> getDonsByStatut(StatutDon statut);

    Page<Don> findByStatut(StatutDon statut, Pageable pageable);

    long countByStatut(StatutDon statut);

    @Query("SELECT COALESCE(SUM(d.quantite), 0) FROM Don d")
    double sumQuantiteTotale();

    List<Don> findByHopital_Id(Long hopitalId);

    @Query("""
            SELECT DISTINCT d
            FROM Don d
            LEFT JOIN d.hopital h
            LEFT JOIN d.unites u
            LEFT JOIN u.stockSang s
            LEFT JOIN s.hopital sh
            WHERE h.id = :hopitalId OR sh.id = :hopitalId
            """)
    List<Don> findAllByHopitalAssociation(@Param("hopitalId") Long hopitalId);
}

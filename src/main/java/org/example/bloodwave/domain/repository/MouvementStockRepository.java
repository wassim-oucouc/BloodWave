package org.example.bloodwave.domain.repository;

import org.example.bloodwave.domain.entity.MouvementStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MouvementStockRepository extends JpaRepository<MouvementStock,Long> {
    
    @Query("SELECT m FROM MouvementStock m WHERE m.stockSang.hopital.id = :hopitalId")
    List<MouvementStock> findByHopitalId(@Param("hopitalId") Long hopitalId);
}

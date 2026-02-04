package org.example.bloodwave.repository;

import org.example.bloodwave.entity.MouvementStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MouvementStockRepository extends JpaRepository<MouvementStock,Long> {
}

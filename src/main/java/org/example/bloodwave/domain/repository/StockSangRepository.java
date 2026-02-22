package org.example.bloodwave.domain.repository;

import org.example.bloodwave.domain.entity.StockSang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StockSangRepository extends JpaRepository<StockSang,Long> {
}

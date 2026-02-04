package org.example.bloodwave.repository;

import org.example.bloodwave.entity.StockSang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StockSangRepository extends JpaRepository<StockSang,Long> {
}

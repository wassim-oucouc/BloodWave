package org.example.bloodwave.domain.repository;

import org.example.bloodwave.domain.entity.Hopital;
import org.example.bloodwave.domain.entity.StockSang;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StockSangRepository extends JpaRepository<StockSang,Long> {
    Optional<StockSang> findByHopitalAndGroupeSanguin(Hopital hopital, GroupeSanguin groupe);
}

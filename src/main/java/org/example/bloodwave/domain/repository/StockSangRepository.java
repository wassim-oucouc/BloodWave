package org.example.bloodwave.domain.repository;

import org.example.bloodwave.domain.entity.StockSang;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface StockSangRepository extends JpaRepository<StockSang,Long> {

    List<StockSang> findByHopitalId(Long hopitalId);

    Optional<StockSang> findByHopitalIdAndGroupeSanguin(Long hopitalId, GroupeSanguin groupeSanguin);
}

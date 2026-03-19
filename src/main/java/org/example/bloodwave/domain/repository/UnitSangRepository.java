package org.example.bloodwave.domain.repository;

import org.example.bloodwave.domain.entity.UniteSang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UnitSangRepository extends JpaRepository<UniteSang,Long> {
	List<UniteSang> findByStockSangId(Long stockSangId);
}

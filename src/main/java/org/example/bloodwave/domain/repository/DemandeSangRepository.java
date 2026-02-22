package org.example.bloodwave.domain.repository;

import org.example.bloodwave.domain.entity.DemandeSang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface DemandeSangRepository extends JpaRepository<DemandeSang,Long>{

    List<DemandeSang> findByDemandeurId(Long demandeurId);
}

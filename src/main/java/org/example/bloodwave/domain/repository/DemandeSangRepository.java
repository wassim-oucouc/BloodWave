package org.example.bloodwave.domain.repository;

import org.example.bloodwave.domain.entity.DemandeSang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DemandeSangRepository extends JpaRepository<DemandeSang,Long>{
}

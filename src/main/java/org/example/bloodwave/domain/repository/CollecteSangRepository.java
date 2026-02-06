package org.example.bloodwave.domain.repository;


import org.example.bloodwave.domain.entity.CollecteSang;
import org.example.bloodwave.domain.enumeration.StatutCollecte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface CollecteSangRepository extends JpaRepository<CollecteSang,Long> {
    List<CollecteSang> findByStatut(StatutCollecte statut);
}

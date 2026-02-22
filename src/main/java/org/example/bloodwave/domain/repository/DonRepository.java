package org.example.bloodwave.domain.repository;


import org.example.bloodwave.domain.entity.Don;
import org.example.bloodwave.domain.entity.Donneur;
import org.example.bloodwave.domain.enumeration.StatutDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonRepository extends JpaRepository<Don,Long> {
    List<Don> getDonsByDonneur(Donneur donneur);

    List<Don> getDonsByStatut(StatutDon statut);

    Page<Don> findByStatut(StatutDon statut, Pageable pageable);
}

package org.example.bloodwave.domain.repository;

import org.example.bloodwave.application.dto.response.DonneurDtoResponse;
import org.example.bloodwave.domain.entity.Donneur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DonneurRepository extends JpaRepository<Donneur,Long> {
    List<Donneur> findDonneurByVille(String ville);
}

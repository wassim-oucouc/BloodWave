package org.example.bloodwave.domain.repository;

import org.example.bloodwave.application.dto.response.DonneurDtoResponse;
import org.example.bloodwave.domain.entity.Donneur;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface DonneurRepository extends JpaRepository<Donneur,Long> {
    List<Donneur> findDonneurByVille(String ville);
    List<Donneur> findByGroupeSanguinInAndDisponibleTrue(
            Set<GroupeSanguin> groupes
    );
}

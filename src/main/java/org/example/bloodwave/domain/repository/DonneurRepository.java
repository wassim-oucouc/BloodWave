package org.example.bloodwave.domain.repository;

import org.example.bloodwave.domain.entity.Donneur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DonneurRepository extends JpaRepository<Donneur,Long> {
}

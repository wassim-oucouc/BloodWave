package org.example.bloodwave.repository;

import org.example.bloodwave.entity.Hopital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HopitalRepository extends JpaRepository<Hopital,Long>
{
}

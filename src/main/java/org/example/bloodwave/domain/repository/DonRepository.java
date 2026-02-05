package org.example.bloodwave.domain.repository;


import org.example.bloodwave.domain.entity.Don;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonRepository extends JpaRepository<Don,Long> {
}

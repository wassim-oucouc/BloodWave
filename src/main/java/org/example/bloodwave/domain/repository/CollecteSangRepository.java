package org.example.bloodwave.domain.repository;


import org.example.bloodwave.domain.entity.CollecteSang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollecteSangRepository extends JpaRepository<CollecteSang,Long> {
}

package org.example.bloodwave.repository;


import org.example.bloodwave.entity.CollecteSang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollecteSangRepository extends JpaRepository<CollecteSang,Long> {
}

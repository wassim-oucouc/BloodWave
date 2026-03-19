package org.example.bloodwave.domain.repository;

import org.example.bloodwave.domain.entity.CollecteSang;
import org.example.bloodwave.domain.entity.Donneur;
import org.example.bloodwave.domain.entity.InscriptionCollecte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InscriptionCollecteRepository extends JpaRepository<InscriptionCollecte,Long> {

   InscriptionCollecte findByDonneurId(Long donneurId);

   List<InscriptionCollecte> findAllByDonneurId(Long donneurId);

   @Query("select i from InscriptionCollecte i join fetch i.donneur d join fetch i.collecte c where d.id = :donneurId")
   List<InscriptionCollecte> findAllByDonneurIdWithDetails(@Param("donneurId") Long donneurId);

   @Query("select i.donneur from InscriptionCollecte i where i.id = :inscriptionId")
   Optional<Donneur> findDonneurByInscriptionId(@Param("inscriptionId") Long inscriptionId);

   @Query("select i.collecte from InscriptionCollecte i where i.id = :inscriptionId")
   Optional<CollecteSang> findCollecteByInscriptionId(@Param("inscriptionId") Long inscriptionId);
}

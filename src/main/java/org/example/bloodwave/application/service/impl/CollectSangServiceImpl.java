package org.example.bloodwave.application.service.impl;

import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.request.CollecteSangDTO;
import org.example.bloodwave.application.dto.response.CollecteSangDtoResponse;
import org.example.bloodwave.application.exceptions.CollecteAlreadyScheduledException;
import org.example.bloodwave.application.exceptions.CollecteNotFoundException;
import org.example.bloodwave.application.exceptions.HopitalNotFoundException;
import org.example.bloodwave.application.mapper.CollecteSangMapper;
import org.example.bloodwave.application.service.CollecteSangService;
import org.example.bloodwave.domain.entity.CollecteSang;
import org.example.bloodwave.domain.entity.Donneur;
import org.example.bloodwave.domain.entity.Hopital;
import org.example.bloodwave.domain.entity.InscriptionCollecte;
import org.example.bloodwave.domain.enumeration.StatutCollecte;
import org.example.bloodwave.domain.repository.CollecteSangRepository;
import org.example.bloodwave.domain.repository.HopitalRepository;
import org.example.bloodwave.domain.repository.InscriptionCollecteRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
public class CollectSangServiceImpl implements CollecteSangService {

    private final CollecteSangRepository collecteSangRepository;
    private final HopitalRepository hopitalRepository;
    private final CollecteSangMapper collecteSangMapper;
    private  final InscriptionCollecteRepository inscriptionCollecteRepository;



    public CollecteSangDtoResponse createCollecte(CollecteSangDTO dto) {
        Hopital hopital = hopitalRepository.findById(dto.getHopitalId())
                .orElseThrow(() -> new HopitalNotFoundException("Hopital not found with id " + dto.getHopitalId()));

        CollecteSang collecte = collecteSangMapper.toEntity(dto);
        collecte.setHopital(hopital);
        collecte.setStatut(StatutCollecte.PLANIFIEE);
        collecte.setDescription(dto.getDescription());



        CollecteSang created = collecteSangRepository.save(collecte);
        return collecteSangMapper.toDtoResponse(created);
    }


    public CollecteSangDtoResponse updateCollecte(Long id, CollecteSangDTO dto) {
        CollecteSang collecte = collecteSangRepository.findById(id)
                .orElseThrow(() -> new CollecteNotFoundException("Collecte not found with id " + id));
            Hopital hopital = hopitalRepository.findById(dto.getHopitalId())
                    .orElseThrow(() -> new HopitalNotFoundException("Hopital not found with id " + dto.getHopitalId()));
            collecte.setHopital(hopital);

        collecte.setDateCollecte(dto.getDateCollecte());
        collecte.setLieu(dto.getLieu());
        collecte.setCapaciteMax(dto.getCapaciteMax());
        collecte.setStatut(dto.getStatut());

        CollecteSang updated = collecteSangRepository.save(collecte);
        return collecteSangMapper.toDtoResponse(updated);
    }

    public void deleteCollecte(Long id) {
        CollecteSang collecte = collecteSangRepository.findById(id)
                .orElseThrow(() -> new CollecteNotFoundException("Collecte not found with id " + id));
        collecteSangRepository.delete(collecte);
    }

    public CollecteSangDtoResponse getCollecteById(Long id) {
        CollecteSang collecte = collecteSangRepository.findById(id)
                .orElseThrow(() -> new CollecteNotFoundException("Collecte not found with id " + id));

        return collecteSangMapper.toDtoResponse(collecte);
    }

    @Cacheable("collections")
    public List<CollecteSangDtoResponse> getAllCollectes() {
        return collecteSangRepository.findAll()
                .stream()
                .map(collecteSangMapper::toDtoResponse)
                .toList();
    }


    public CollecteSangDtoResponse joinCollecte(Long collecteId, Donneur donneur) {
        CollecteSang collecte = collecteSangRepository.findById(collecteId)
                .orElseThrow(() -> new CollecteNotFoundException("Collecte not found with id " + collecteId));

        InscriptionCollecte inscription = new InscriptionCollecte();
        inscription.setCollecte(collecte);
        inscription.setDonneur(donneur);
        inscription.setJoined(true);

        this.inscriptionCollecteRepository.save(inscription);

        collecte.getInscriptions().add(inscription);

        CollecteSang updated = collecteSangRepository.save(collecte);

        return collecteSangMapper.toDtoResponse(updated);
    }

    public CollecteSangDtoResponse cancelParticipation(Long collecteId, Donneur donneur) {
        CollecteSang collecte = collecteSangRepository.findById(collecteId)
                .orElseThrow(() -> new CollecteNotFoundException("Collecte not found with id " + collecteId));
      InscriptionCollecte inscriptionCollecte =   this.inscriptionCollecteRepository.findByDonneurId(donneur.getId());

      inscriptionCollecte.setJoined(false);

      this.inscriptionCollecteRepository.save(inscriptionCollecte);


        CollecteSang updated = collecteSangRepository.save(collecte);
        return collecteSangMapper.toDtoResponse(updated);
    }

    public List<CollecteSangDtoResponse> getCollectesByStatus(StatutCollecte statut) {
        return collecteSangRepository.findByStatut(statut)
                .stream()
                .map(collecteSangMapper::toDtoResponse)
                .toList();
    }

    public List<CollecteSangDtoResponse> getCollectsByHopitalId(Long hopitalId)
    {
        return collecteSangRepository
                .findAll()
                .stream()
                .filter(collecteSang
                        -> Objects.equals(collecteSang.getHopital().getId(), hopitalId))
                .map(collecteSangMapper::toDtoResponse).toList();
    }





}

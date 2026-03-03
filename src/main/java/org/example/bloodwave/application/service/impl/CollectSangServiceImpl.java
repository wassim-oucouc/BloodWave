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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class CollectSangServiceImpl implements CollecteSangService {

    private final CollecteSangRepository collecteSangRepository;
    private final HopitalRepository hopitalRepository;
    private final CollecteSangMapper collecteSangMapper;



    public CollecteSangDtoResponse createCollecte(CollecteSangDTO dto) {
        Hopital hopital = hopitalRepository.findById(dto.getHopitalId())
                .orElseThrow(() -> new HopitalNotFoundException("Hopital not found with id " + dto.getHopitalId()));

        if (collecteSangRepository.existsByHopitalAndDateCollecte(hopital, dto.getDateCollecte())) {
            throw new CollecteAlreadyScheduledException("Une collecte est déjà planifiée pour ce hopital à cette date et heure.");
        }

        CollecteSang collecte = collecteSangMapper.toEntity(dto);
        collecte.setHopital(hopital);
        collecte.setStatut(StatutCollecte.PLANIFIEE);

        CollecteSang created = collecteSangRepository.save(collecte);
        return collecteSangMapper.toDtoResponse(created);
    }


    public CollecteSangDtoResponse updateCollecte(Long id, CollecteSangDTO dto) {
        CollecteSang collecte = collecteSangRepository.findById(id)
                .orElseThrow(() -> new CollecteNotFoundException("Collecte not found with id " + id));
            Hopital hopital = hopitalRepository.findById(dto.getHopitalId())
                    .orElseThrow(() -> new HopitalNotFoundException("Hopital not found with id " + dto.getHopitalId()));
            collecte.setHopital(hopital);

        if (collecteSangRepository.existsByHopitalAndDateCollecte(hopital, dto.getDateCollecte())) {
            throw new CollecteAlreadyScheduledException("Une collecte est déjà planifiée pour ce hopital à cette date et heure.");
        }

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

        collecte.getInscriptions().add(inscription);

        CollecteSang updated = collecteSangRepository.save(collecte);

        return collecteSangMapper.toDtoResponse(updated);
    }

    public CollecteSangDtoResponse cancelParticipation(Long collecteId, Donneur donneur) {
        CollecteSang collecte = collecteSangRepository.findById(collecteId)
                .orElseThrow(() -> new CollecteNotFoundException("Collecte not found with id " + collecteId));

        collecte.setStatut(StatutCollecte.ANNULEE);

        CollecteSang updated = collecteSangRepository.save(collecte);
        return collecteSangMapper.toDtoResponse(updated);
    }

    public List<CollecteSangDtoResponse> getCollectesByStatus(StatutCollecte statut) {
        return collecteSangRepository.findByStatut(statut)
                .stream()
                .map(collecteSangMapper::toDtoResponse)
                .toList();
    }





}

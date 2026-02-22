package org.example.bloodwave.application.service.impl;

import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.request.DemandeSangDTO;
import org.example.bloodwave.application.dto.response.DemandeSangDtoResponse;
import org.example.bloodwave.application.exceptions.DemandeSangNotFoundException;
import org.example.bloodwave.application.mapper.DemandeSangMapper;
import org.example.bloodwave.application.service.DemandeSangService;
import org.example.bloodwave.application.service.EmailService;
import org.example.bloodwave.domain.entity.DemandeSang;
import org.example.bloodwave.domain.entity.Demandeur;
import org.example.bloodwave.domain.entity.Hopital;
import org.example.bloodwave.domain.enumeration.StatutDemande;
import org.example.bloodwave.domain.repository.DemandeSangRepository;
import org.example.bloodwave.domain.repository.DemandeurRepository;
import org.example.bloodwave.domain.repository.HopitalRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class DemandeSangServiceImpl implements DemandeSangService {

    private final DemandeSangRepository demandeSangRepository;
    private final DemandeurRepository demandeurRepository;
    private final HopitalRepository hopitalRepository;
    private final DemandeSangMapper demandeSangMapper;
    private final EmailService emailService;

    @Override
    public DemandeSangDtoResponse create(DemandeSangDTO dto) {

        Demandeur demandeur = demandeurRepository.findById(dto.getDemandeurId())
                .orElseThrow(() -> new RuntimeException("Demandeur non trouvé"));

        Hopital hopital = hopitalRepository.findById(dto.getHopitalId())
                .orElseThrow(() -> new RuntimeException("Hopital non trouvé"));

        DemandeSang demandeSang = demandeSangMapper.toEntity(dto);

        demandeSang.setDemandeur(demandeur);
        demandeSang.setHopital(hopital);
        demandeSang.setDateCreation(LocalDateTime.now());

        demandeSang.setStatut(StatutDemande.EN_ATTENTE);

        DemandeSang saved = demandeSangRepository.save(demandeSang);

        return demandeSangMapper.toDtoResponse(saved);
    }


    @Override
    public List<DemandeSangDtoResponse> getAll() {

        return demandeSangRepository.findAll()
                .stream()
                .map(demandeSangMapper::toDtoResponse)
                .collect(Collectors.toList());
    }


    @Override
    public DemandeSangDtoResponse getById(Long id) {

        DemandeSang demandeSang = demandeSangRepository.findById(id)
                .orElseThrow(() -> new DemandeSangNotFoundException("Demande non trouvée"));

        return demandeSangMapper.toDtoResponse(demandeSang);
    }


    @Override
    public List<DemandeSangDtoResponse> getByDemandeur(Long demandeurId) {

        return demandeSangRepository.findByDemandeurId(demandeurId)
                .stream()
                .map(demandeSangMapper::toDtoResponse)
                .collect(Collectors.toList());
    }


    @Override
    public DemandeSangDtoResponse updateStatut(Long id, StatutDemande statut) {

        DemandeSang demandeSang = demandeSangRepository.findById(id)
                .orElseThrow(() -> new DemandeSangNotFoundException("Demande non trouvée"));

        demandeSang.setStatut(statut);

        DemandeSang updated = demandeSangRepository.save(demandeSang);

        return demandeSangMapper.toDtoResponse(updated);
    }

    public void approveDemande(Long demandeId)
    {
        DemandeSang demandeSang = demandeSangRepository.findById(demandeId)
                .orElseThrow(() -> new DemandeSangNotFoundException("Demande non trouvée"));

        demandeSang.setStatut(StatutDemande.ACCEPTEE);

        this.emailService.sendEmail(demandeSang.getDemandeur().getEmail(),
                "Your Blood Request Has Been Approved",
                "Hello "+ demandeSang.getDemandeur().getNom() + ",\\n\\n\" +\n" +
                        "    \"Your blood request with ID \" + demande.getId() + \" has been APPROVED.\\n\" +\n" +
                        "    \"Please follow any instructions provided by the hospital.\\n\\n\" +\n" +
                        "    \"Thank you for using BloodWave.\"");

        this.demandeSangRepository.save(demandeSang);

    }


    public void rejectDemande(Long demandeId)
    {
                DemandeSang
                demandeSang =
                demandeSangRepository
                .findById(demandeId)
                .orElseThrow(() -> new DemandeSangNotFoundException("Demande non trouvée"));

        demandeSang.setStatut(StatutDemande.REFUSEE);

        this.emailService.sendEmail(demandeSang.getDemandeur().getEmail(),
                "Your Blood Request Has Been Refused",
                "Hello "+ demandeSang.getDemandeur().getNom() + ",\\n\\n\" +\n" +
                        "    \"Your blood request with ID \" + demande.getId() + \" has been REFUSED.\\n\" +\n" +
                        "    \"Please follow any instructions provided by the hospital.\\n\\n\" +\n" +
                        "    \"Thank you for using BloodWave.\"");

        this.demandeSangRepository.save(demandeSang);

    }

}

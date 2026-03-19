package org.example.bloodwave.application.mapper;

import org.example.bloodwave.application.dto.request.InscriptionCollecteDTO;
import org.example.bloodwave.application.dto.response.InscriptionCollecteDtoResponse;
import org.example.bloodwave.domain.entity.CollecteSang;
import org.example.bloodwave.domain.entity.Donneur;
import org.example.bloodwave.domain.entity.InscriptionCollecte;
import org.example.bloodwave.domain.repository.CollecteSangRepository;
import org.example.bloodwave.domain.repository.DonneurRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {DonneurMapper.class, CollecteSangMapper.class})
public abstract class InscriptionCollectMapper {

    @Autowired
    protected DonneurRepository donneurRepository;

    @Autowired
    protected CollecteSangRepository collecteSangRepository;

    @Mapping(target = "donneurDtoResponse", source = "donneur")
    @Mapping(target = "collecteSangDtoResponse", source = "collecte")
    public abstract InscriptionCollecteDtoResponse toDtoResponse(InscriptionCollecte inscriptionCollecte);

    public InscriptionCollecte toEntity(InscriptionCollecteDTO dto) {
        if (dto == null) return null;
        InscriptionCollecte inscription = new InscriptionCollecte();
        inscription.setId(dto.getId());
        inscription.setJoined(dto.isJoined());
        return inscription;
    }

    @AfterMapping
    protected void fillEntitiesFromId(InscriptionCollecteDTO dto, @MappingTarget InscriptionCollecte entity) {
        if (dto.getDonneurId() != null) {
            Donneur donneur = donneurRepository.findById(dto.getDonneurId())
                    .orElseThrow(() -> new RuntimeException("Donneur not found with id " + dto.getDonneurId()));
            entity.setDonneur(donneur);
        }

        if (dto.getCollectSangId() != null) {
            CollecteSang collecte = collecteSangRepository.findById(dto.getCollectSangId())
                    .orElseThrow(() -> new RuntimeException("CollecteSang not found with id " + dto.getCollectSangId()));
            entity.setCollecte(collecte);
        }
    }
}
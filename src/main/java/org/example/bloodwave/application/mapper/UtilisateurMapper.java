package org.example.bloodwave.application.mapper;


import org.example.bloodwave.application.dto.request.DemandeurDTO;
import org.example.bloodwave.application.dto.request.DonneurDTO;
import org.example.bloodwave.application.dto.request.HopitalDTO;
import org.example.bloodwave.application.dto.request.UtilisateurDTO;
import org.example.bloodwave.application.dto.response.DemandeurDtoResponse;
import org.example.bloodwave.application.dto.response.DonneurDtoResponse;
import org.example.bloodwave.application.dto.response.HopitalDtoResponse;
import org.example.bloodwave.application.dto.response.UtilisateurDtoResponse;
import org.example.bloodwave.domain.entity.Demandeur;
import org.example.bloodwave.domain.entity.Donneur;
import org.example.bloodwave.domain.entity.Hopital;
import org.example.bloodwave.domain.entity.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = "spring",uses = {DonneurMapper.class, DemandeurMapper.class, HopitalMapper.class})
public abstract class UtilisateurMapper {

    @SubclassMapping(source = HopitalDTO.class, target = Hopital.class)
    @SubclassMapping(source = DemandeurDTO.class, target = Demandeur.class)
    @SubclassMapping(source = DonneurDTO.class, target = DonneurDTO.class)
    public abstract Utilisateur toEntity(UtilisateurDTO utilisateurDTO);

    @SubclassMapping(source = Hopital.class, target = HopitalDtoResponse.class)
    @SubclassMapping(source = Demandeur.class, target = DemandeurDtoResponse.class)
    @SubclassMapping(source = Donneur.class, target = DonneurDtoResponse.class)
    public abstract UtilisateurDtoResponse toDtoResponse(Utilisateur utilisateur);

}

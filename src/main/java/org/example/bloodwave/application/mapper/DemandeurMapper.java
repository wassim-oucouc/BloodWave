package org.example.bloodwave.application.mapper;


import org.example.bloodwave.application.dto.request.DemandeurDTO;
import org.example.bloodwave.application.dto.response.DemandeurDtoResponse;
import org.example.bloodwave.domain.entity.Demandeur;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class DemandeurMapper {

    public abstract Demandeur toEntity(DemandeurDTO dto);

    public abstract DemandeurDtoResponse toDtoResponse(Demandeur demandeur);
}

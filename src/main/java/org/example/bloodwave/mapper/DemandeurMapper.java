package org.example.bloodwave.mapper;


import org.example.bloodwave.dto.request.DemandeurDTO;
import org.example.bloodwave.dto.response.DemandeurDtoResponse;
import org.example.bloodwave.entity.Demandeur;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class DemandeurMapper {

    public abstract Demandeur toEntity(DemandeurDTO dto);

    public abstract DemandeurDtoResponse toDtoResponse(Demandeur demandeur);
}

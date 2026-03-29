package org.example.bloodwave.application.mapper;


import org.example.bloodwave.application.dto.request.DemandeurDTO;
import org.example.bloodwave.application.dto.response.DemandeurDtoResponse;
import org.example.bloodwave.domain.entity.Demandeur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DemandeurMapper {

    @Mapping(target = "demandeSangs", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "actif", ignore = true)
    @Mapping(target = "dateCreation", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "imageProfile", source = "imageProfile")

    Demandeur toEntity(DemandeurDTO dto);

    @Mapping(target = "demandeurDtoResponses", ignore = true)
    DemandeurDtoResponse toDtoResponse(Demandeur demandeur);
}

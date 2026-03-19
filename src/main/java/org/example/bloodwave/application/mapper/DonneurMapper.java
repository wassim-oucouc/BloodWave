package org.example.bloodwave.application.mapper;



import org.example.bloodwave.application.dto.request.DonneurDTO;
import org.example.bloodwave.application.dto.response.DonneurDtoResponse;
import org.example.bloodwave.domain.entity.Donneur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public abstract class DonneurMapper{

    @Mappings({
            @Mapping(source = "dateDernierDon", target = "lastDonationDate"),
            @Mapping(source = "dateNaissance", target = "dateOfBirth"),
            @Mapping(source = "poids", target = "weight"),
            @Mapping(target = "dons", ignore = true),
            @Mapping(target = "inscriptions", ignore = true)
    })

    public abstract Donneur toEntity(DonneurDTO dto);

    @Mappings({
            @Mapping(source = "lastDonationDate", target = "dateDernierDon"),
            @Mapping(source = "dateOfBirth", target = "dateNaissance"),
            @Mapping(source = "weight", target = "poids"),
            @Mapping(source = "dons", target = "dtoResponses")
    })
    public abstract DonneurDtoResponse toDtoResponse(Donneur donneur);
}

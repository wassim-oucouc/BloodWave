package org.example.bloodwave.application.mapper;



import org.example.bloodwave.application.dto.request.DonneurDTO;
import org.example.bloodwave.application.dto.response.DonneurDtoResponse;
import org.example.bloodwave.domain.entity.Donneur;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class DonneurMapper{

    public abstract Donneur toEntity(DonneurDTO dto);

    public abstract DonneurDtoResponse toDtoResponse(Donneur donneur);
}

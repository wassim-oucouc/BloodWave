package org.example.bloodwave.mapper;



import org.example.bloodwave.dto.request.DonneurDTO;
import org.example.bloodwave.dto.response.DonneurDtoResponse;
import org.example.bloodwave.entity.Donneur;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class DonneurMapper {

    public abstract Donneur toEntity(DonneurDTO dto);

    public abstract DonneurDtoResponse toDtoResponse(Donneur donneur);
}

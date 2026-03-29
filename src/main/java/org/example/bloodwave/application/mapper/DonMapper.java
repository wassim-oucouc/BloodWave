package org.example.bloodwave.application.mapper;


import org.example.bloodwave.application.dto.request.DonDTO;
import org.example.bloodwave.application.dto.response.DonDtoResponse;
import org.example.bloodwave.domain.entity.Don;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DonMapper {

    @Mapping(target = "donneurDtoResponse", source = "donneur")
    @Mapping(target = "hopitalDtoResponse", source = "hopital")
    @Mapping(target = "uniteSangDtoResponses", source = "unites", ignore = true)
    DonDtoResponse toDtoResponse(Don don);

    @Mapping(target = "donneur", ignore = true)
    @Mapping(target = "hopital", ignore = true)
    @Mapping(target = "unites", ignore = true)
    @Mapping(target = "id", ignore = true)
    Don toEntity(DonDTO dto);
}

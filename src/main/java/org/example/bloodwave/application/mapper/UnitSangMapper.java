package org.example.bloodwave.application.mapper;


import ch.qos.logback.core.model.ComponentModel;
import org.example.bloodwave.application.dto.request.UniteSangDTO;
import org.example.bloodwave.application.dto.response.UniteSangDtoResponse;
import org.example.bloodwave.domain.entity.UniteSang;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UnitSangMapper {

    @Mapping(target = "don", ignore = true)
    @Mapping(target = "stockSang", ignore = true)
    @Mapping(target = "id", ignore = true)
    UniteSang toEntity(UniteSangDTO uniteSangDTO);

    @Mapping(target = "stockSangDtoResponse", ignore = true)
    @Mapping(target = "don", ignore = true)
    UniteSangDtoResponse toDtoResponse(UniteSang uniteSang);
}

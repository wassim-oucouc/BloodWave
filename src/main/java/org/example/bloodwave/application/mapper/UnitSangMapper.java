package org.example.bloodwave.application.mapper;


import ch.qos.logback.core.model.ComponentModel;
import org.example.bloodwave.application.dto.request.UniteSangDTO;
import org.example.bloodwave.application.dto.response.UniteSangDtoResponse;
import org.example.bloodwave.domain.entity.UniteSang;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UnitSangMapper {


     public abstract UniteSang toEntity(UniteSangDTO uniteSangDTO);

     public abstract UniteSangDtoResponse toDtoResponse(UniteSang uniteSang);
}

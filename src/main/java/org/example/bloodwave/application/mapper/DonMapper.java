package org.example.bloodwave.application.mapper;


import org.example.bloodwave.application.dto.request.DonDTO;
import org.example.bloodwave.application.dto.response.DonDtoResponse;
import org.example.bloodwave.domain.entity.Don;
import org.example.bloodwave.domain.entity.Hopital;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {DonneurMapper.class, Hopital.class})
public abstract class DonMapper {


    public abstract DonDtoResponse toDtoResponse(Don don);

    public abstract Don toEntity(DonDTO dto);
}

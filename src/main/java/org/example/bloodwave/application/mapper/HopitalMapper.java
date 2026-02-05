package org.example.bloodwave.application.mapper;



import org.example.bloodwave.application.dto.request.HopitalDTO;
import org.example.bloodwave.application.dto.response.HopitalDtoResponse;
import org.example.bloodwave.domain.entity.Hopital;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class HopitalMapper {



    public abstract Hopital toEntity(HopitalDTO dto);

    public abstract HopitalDtoResponse toDtoResponse(Hopital hopital);
}

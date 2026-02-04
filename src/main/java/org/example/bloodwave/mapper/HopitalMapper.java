package org.example.bloodwave.mapper;



import org.example.bloodwave.dto.request.HopitalDTO;
import org.example.bloodwave.dto.response.HopitalDtoResponse;
import org.example.bloodwave.entity.Hopital;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class HopitalMapper {

    public abstract Hopital toEntity(HopitalDTO dto);

    public abstract HopitalDtoResponse toDtoResponse(Hopital hopital);
}

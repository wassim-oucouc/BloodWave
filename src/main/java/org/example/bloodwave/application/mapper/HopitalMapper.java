package org.example.bloodwave.application.mapper;



import org.example.bloodwave.application.dto.request.HopitalDTO;
import org.example.bloodwave.application.dto.response.HopitalDtoResponse;
import org.example.bloodwave.domain.entity.Hopital;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HopitalMapper {

    @Mapping(target = "dateCreation", ignore = true)
    @Mapping(target = "stocks", ignore = true)
    @Mapping(target = "donsRecus", ignore = true)
    @Mapping(target = "collectes", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "actif", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "imageProfile", source = "imageProfile")
    Hopital toEntity(HopitalDTO dto);

    @Mapping(target = "stockSangDTOS", ignore = true)
    @Mapping(target = "dons", ignore = true)
    @Mapping(target = "collecteSangDTOS", ignore = true)
    HopitalDtoResponse toDtoResponse(Hopital hopital);
}

package org.example.bloodwave.application.mapper;


import org.example.bloodwave.application.dto.request.CollecteSangDTO;
import org.example.bloodwave.application.dto.response.CollecteSangDtoResponse;
import org.example.bloodwave.domain.entity.CollecteSang;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = HopitalMapper.class)
public abstract class CollecteSangMapper {

    @Mapping(target = "hopitalDtoResponse", source = "hopital")
    @Mapping(target = "inscriptionCollecteDtoResponses", ignore = true)
    public abstract CollecteSangDtoResponse toDtoResponse(CollecteSang collecteSang);

    @Mapping(target = "hopital", ignore = true)
    @Mapping(target = "inscriptions", ignore = true)
    public abstract CollecteSang toEntity(CollecteSangDTO collecteSangDTO);

}

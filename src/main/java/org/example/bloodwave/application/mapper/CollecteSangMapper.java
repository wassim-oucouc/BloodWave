package org.example.bloodwave.application.mapper;


import org.example.bloodwave.application.dto.request.CollecteSangDTO;
import org.example.bloodwave.application.dto.response.CollecteSangDtoResponse;
import org.example.bloodwave.domain.entity.CollecteSang;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CollecteSangMapper {

    public abstract CollecteSangDtoResponse toDtoResponse(CollecteSang collecteSang);

    public abstract CollecteSang toEntity(CollecteSangDTO collecteSangDTO);

}

package org.example.bloodwave.application.mapper;

import org.example.bloodwave.application.dto.request.StockSangDTO;
import org.example.bloodwave.application.dto.response.StockSangDtoResponse;
import org.example.bloodwave.domain.entity.StockSang;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {HopitalMapper.class})
public interface StockSangMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hopital", ignore = true)
    StockSang toEntity(StockSangDTO dto);

    @Mapping(source = "hopital", target = "hopitalDtoResponse")
    StockSangDtoResponse toDtoResponse(StockSang entity);
}
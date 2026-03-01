package org.example.bloodwave.application.mapper;

import org.example.bloodwave.application.dto.request.StockSangDTO;
import org.example.bloodwave.application.dto.response.StockSangDtoResponse;
import org.example.bloodwave.domain.entity.StockSang;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {
                HopitalMapper.class,
                UnitSangMapper.class,
                MouvementStockMapper.class
        })
public interface StockSangMapper {


    @Mapping(source = "hopital", target = "hopitalDtoResponse")
    @Mapping(source = "unites", target = "uniteSangDtoResponses")
    @Mapping(source = "mouvements", target = "mouvements")
    StockSangDtoResponse toDtoResponse(StockSang entity);


    @Mapping(source = "hopitalId", target = "hopital.id")
    @Mapping(target = "unites", ignore = true)
    @Mapping(target = "mouvements", ignore = true)
    StockSang toEntity(StockSangDTO dto);
}
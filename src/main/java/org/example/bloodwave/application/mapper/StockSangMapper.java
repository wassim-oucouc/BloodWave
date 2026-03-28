package org.example.bloodwave.application.mapper;

import org.example.bloodwave.application.dto.request.StockSangDTO;
import org.example.bloodwave.application.dto.response.StockSangDtoResponse;
import org.example.bloodwave.domain.entity.StockSang;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper(componentModel = "spring",
        uses = {
                HopitalMapper.class,
                UnitSangMapper.class,
                MouvementStockMapper.class
        })
 public abstract class StockSangMapper {

    @Lazy
    @Autowired
    public MouvementStockMapper mouvementStockMapper;



    @Mapping(source = "hopital", target = "hopitalDtoResponse")
    @Mapping(source = "unites", target = "uniteSangDtoResponses")
    @Mapping(source = "mouvements", target = "mouvements", ignore = true)
    public abstract StockSangDtoResponse toDtoResponse(StockSang entity);


    @Mapping(source = "hopitalId", target = "hopital.id")
    @Mapping(target = "unites", ignore = true)
    @Mapping(target = "mouvements", ignore = true)
public abstract StockSang toEntity(StockSangDTO dto);
}
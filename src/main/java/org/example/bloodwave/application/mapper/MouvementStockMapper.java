package org.example.bloodwave.application.mapper;

import org.example.bloodwave.application.dto.request.MouvementStockDTO;
import org.example.bloodwave.application.dto.response.MouvementStockDtoResponse;
import org.example.bloodwave.application.exceptions.StockSangNotFoundException;
import org.example.bloodwave.application.exceptions.UnitSangNotFoundException;
import org.example.bloodwave.domain.entity.MouvementStock;
import org.example.bloodwave.domain.entity.StockSang;
import org.example.bloodwave.domain.entity.UniteSang;
import org.example.bloodwave.domain.repository.StockSangRepository;
import org.example.bloodwave.domain.repository.UnitSangRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper(
        componentModel = "spring",
        uses = {
                UnitSangMapper.class
        }
)
public abstract class MouvementStockMapper {
    @Autowired
    @Lazy
    protected StockSangMapper stockSangMapper;
    @Autowired
    public StockSangRepository stockSangRepository;
    @Autowired
    public UnitSangRepository unitSangRepository;


    @Mapping(source = "stockSangId", target = "stockSang", qualifiedByName = "stockFromId")
    @Mapping(source = "uniteSangId", target = "uniteSang", qualifiedByName = "uniteFromId")
    public abstract MouvementStock toEntity(MouvementStockDTO dto);

    @Mapping(source = "stockSang", target = "stockSangDtoResponse", ignore = true)
    @Mapping(source = "uniteSang", target = "uniteSangDtoResponse")
    public abstract MouvementStockDtoResponse toDtoResponse(MouvementStock entity);

@Named("stockFromId")
    public StockSang findStockSangById(Long stockSangId)
{
    return this.stockSangRepository
            .findById(stockSangId)
            .orElseThrow(() -> new StockSangNotFoundException("stock not found by id" + stockSangId));
}

    @Named("uniteFromId")
    public UniteSang findSUniteById(Long uniteId)
    {
        return this.unitSangRepository
                .findById(uniteId)
                .orElseThrow(() -> new UnitSangNotFoundException("stock not found by id" + uniteId));
    }


}
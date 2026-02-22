package org.example.bloodwave.application.mapper;


import org.example.bloodwave.application.dto.request.DemandeSangDTO;
import org.example.bloodwave.application.dto.response.DemandeSangDtoResponse;
import org.example.bloodwave.domain.entity.DemandeSang;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
            componentModel = "spring",
            uses = {
                    DemandeurMapper.class,
                    HopitalMapper.class
            }
    )
    public interface DemandeSangMapper {

        @Mapping(target = "id", ignore = true)
        @Mapping(target = "dateCreation", ignore = true)
        @Mapping(target = "statut", ignore = true)
        @Mapping(target = "demandeur", ignore = true)
        @Mapping(target = "hopital", ignore = true)
        DemandeSang toEntity(DemandeSangDTO dto);


        @Mapping(source = "demandeur", target = "demandeurDtoResponse")
        @Mapping(source = "hopital", target = "hopitalDtoResponse")
        DemandeSangDtoResponse toDtoResponse(DemandeSang entity);

    }

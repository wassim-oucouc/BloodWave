package org.example.bloodwave.application.mapper;

import javax.annotation.processing.Generated;
import org.example.bloodwave.application.dto.request.DonDTO;
import org.example.bloodwave.application.dto.response.DonDtoResponse;
import org.example.bloodwave.domain.entity.Don;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-13T16:52:46+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class DonMapperImpl extends DonMapper {

    @Override
    public DonDtoResponse toDtoResponse(Don don) {
        if ( don == null ) {
            return null;
        }

        DonDtoResponse donDtoResponse = new DonDtoResponse();

        donDtoResponse.setId( don.getId() );
        donDtoResponse.setDatePrevue( don.getDatePrevue() );
        donDtoResponse.setDateEffective( don.getDateEffective() );
        donDtoResponse.setQuantite( don.getQuantite() );
        donDtoResponse.setStatut( don.getStatut() );

        return donDtoResponse;
    }

    @Override
    public Don toEntity(DonDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Don don = new Don();

        don.setId( dto.getId() );
        don.setDatePrevue( dto.getDatePrevue() );
        don.setDateEffective( dto.getDateEffective() );
        don.setQuantite( dto.getQuantite() );
        don.setStatut( dto.getStatut() );

        return don;
    }
}

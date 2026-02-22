package org.example.bloodwave.application.mapper;

import javax.annotation.processing.Generated;
import org.example.bloodwave.application.dto.request.HopitalDTO;
import org.example.bloodwave.application.dto.response.HopitalDtoResponse;
import org.example.bloodwave.domain.entity.Hopital;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-13T16:52:45+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class HopitalMapperImpl extends HopitalMapper {

    @Override
    public Hopital toEntity(HopitalDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Hopital hopital = new Hopital();

        hopital.setId( dto.getId() );
        hopital.setNom( dto.getNom() );
        hopital.setPrenom( dto.getPrenom() );
        hopital.setEmail( dto.getEmail() );
        hopital.setMotDePasse( dto.getMotDePasse() );
        hopital.setTelephone( dto.getTelephone() );
        hopital.setAdresse( dto.getAdresse() );
        hopital.setRole( dto.getRole() );
        hopital.setActif( dto.getActif() );
        hopital.setNomHopital( dto.getNomHopital() );
        hopital.setVille( dto.getVille() );
        hopital.setCapaciteStockage( dto.getCapaciteStockage() );

        return hopital;
    }

    @Override
    public HopitalDtoResponse toDtoResponse(Hopital hopital) {
        if ( hopital == null ) {
            return null;
        }

        HopitalDtoResponse hopitalDtoResponse = new HopitalDtoResponse();

        hopitalDtoResponse.setId( hopital.getId() );
        hopitalDtoResponse.setNom( hopital.getNom() );
        hopitalDtoResponse.setPrenom( hopital.getPrenom() );
        hopitalDtoResponse.setEmail( hopital.getEmail() );
        hopitalDtoResponse.setTelephone( hopital.getTelephone() );
        hopitalDtoResponse.setAdresse( hopital.getAdresse() );
        hopitalDtoResponse.setRole( hopital.getRole() );
        hopitalDtoResponse.setActif( hopital.getActif() );

        return hopitalDtoResponse;
    }
}

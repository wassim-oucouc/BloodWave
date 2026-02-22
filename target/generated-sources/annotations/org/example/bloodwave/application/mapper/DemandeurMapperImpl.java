package org.example.bloodwave.application.mapper;

import javax.annotation.processing.Generated;
import org.example.bloodwave.application.dto.request.DemandeurDTO;
import org.example.bloodwave.application.dto.response.DemandeurDtoResponse;
import org.example.bloodwave.domain.entity.Demandeur;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-13T16:52:45+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class DemandeurMapperImpl extends DemandeurMapper {

    @Override
    public Demandeur toEntity(DemandeurDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Demandeur demandeur = new Demandeur();

        demandeur.setId( dto.getId() );
        demandeur.setNom( dto.getNom() );
        demandeur.setPrenom( dto.getPrenom() );
        demandeur.setEmail( dto.getEmail() );
        demandeur.setMotDePasse( dto.getMotDePasse() );
        demandeur.setTelephone( dto.getTelephone() );
        demandeur.setAdresse( dto.getAdresse() );
        demandeur.setRole( dto.getRole() );
        demandeur.setActif( dto.getActif() );

        return demandeur;
    }

    @Override
    public DemandeurDtoResponse toDtoResponse(Demandeur demandeur) {
        if ( demandeur == null ) {
            return null;
        }

        DemandeurDtoResponse demandeurDtoResponse = new DemandeurDtoResponse();

        demandeurDtoResponse.setId( demandeur.getId() );
        demandeurDtoResponse.setNom( demandeur.getNom() );
        demandeurDtoResponse.setPrenom( demandeur.getPrenom() );
        demandeurDtoResponse.setEmail( demandeur.getEmail() );
        demandeurDtoResponse.setTelephone( demandeur.getTelephone() );
        demandeurDtoResponse.setAdresse( demandeur.getAdresse() );
        demandeurDtoResponse.setRole( demandeur.getRole() );
        demandeurDtoResponse.setActif( demandeur.getActif() );

        return demandeurDtoResponse;
    }
}

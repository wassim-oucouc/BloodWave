package org.example.bloodwave.application.mapper;

import javax.annotation.processing.Generated;
import org.example.bloodwave.application.dto.request.DonneurDTO;
import org.example.bloodwave.application.dto.response.DonneurDtoResponse;
import org.example.bloodwave.domain.entity.Donneur;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-13T16:52:45+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class DonneurMapperImpl extends DonneurMapper {

    @Override
    public Donneur toEntity(DonneurDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Donneur donneur = new Donneur();

        donneur.setId( dto.getId() );
        donneur.setNom( dto.getNom() );
        donneur.setPrenom( dto.getPrenom() );
        donneur.setEmail( dto.getEmail() );
        donneur.setMotDePasse( dto.getMotDePasse() );
        donneur.setTelephone( dto.getTelephone() );
        donneur.setAdresse( dto.getAdresse() );
        donneur.setRole( dto.getRole() );
        donneur.setActif( dto.getActif() );
        donneur.setGroupeSanguin( dto.getGroupeSanguin() );
        donneur.setDateDernierDon( dto.getDateDernierDon() );
        donneur.setDisponible( dto.getDisponible() );
        donneur.setNombreDonsTotaux( dto.getNombreDonsTotaux() );

        return donneur;
    }

    @Override
    public DonneurDtoResponse toDtoResponse(Donneur donneur) {
        if ( donneur == null ) {
            return null;
        }

        DonneurDtoResponse donneurDtoResponse = new DonneurDtoResponse();

        donneurDtoResponse.setId( donneur.getId() );
        donneurDtoResponse.setNom( donneur.getNom() );
        donneurDtoResponse.setPrenom( donneur.getPrenom() );
        donneurDtoResponse.setEmail( donneur.getEmail() );
        donneurDtoResponse.setTelephone( donneur.getTelephone() );
        donneurDtoResponse.setAdresse( donneur.getAdresse() );
        donneurDtoResponse.setRole( donneur.getRole() );
        donneurDtoResponse.setActif( donneur.getActif() );

        return donneurDtoResponse;
    }
}

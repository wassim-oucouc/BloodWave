package org.example.bloodwave.application.mapper;

import javax.annotation.processing.Generated;
import org.example.bloodwave.application.dto.request.DemandeurDTO;
import org.example.bloodwave.application.dto.request.DonneurDTO;
import org.example.bloodwave.application.dto.request.HopitalDTO;
import org.example.bloodwave.application.dto.request.UtilisateurDTO;
import org.example.bloodwave.application.dto.response.UtilisateurDtoResponse;
import org.example.bloodwave.domain.entity.Demandeur;
import org.example.bloodwave.domain.entity.Donneur;
import org.example.bloodwave.domain.entity.Hopital;
import org.example.bloodwave.domain.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-13T16:52:46+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class UtilisateurMapperImpl extends UtilisateurMapper {

    @Autowired
    private DonneurMapper donneurMapper;
    @Autowired
    private DemandeurMapper demandeurMapper;
    @Autowired
    private HopitalMapper hopitalMapper;

    @Override
    public Utilisateur toEntity(UtilisateurDTO utilisateurDTO) {
        if ( utilisateurDTO == null ) {
            return null;
        }

        if (utilisateurDTO instanceof HopitalDTO) {
            return hopitalMapper.toEntity( (HopitalDTO) utilisateurDTO );
        }
        else if (utilisateurDTO instanceof DemandeurDTO) {
            return demandeurMapper.toEntity( (DemandeurDTO) utilisateurDTO );
        }
        else if (utilisateurDTO instanceof DonneurDTO) {
            return donneurMapper.toEntity( (DonneurDTO) utilisateurDTO );
        }
        else {
            Utilisateur utilisateur = createEntity( utilisateurDTO );

            utilisateur.setId( utilisateurDTO.getId() );
            utilisateur.setNom( utilisateurDTO.getNom() );
            utilisateur.setPrenom( utilisateurDTO.getPrenom() );
            utilisateur.setEmail( utilisateurDTO.getEmail() );
            utilisateur.setMotDePasse( utilisateurDTO.getMotDePasse() );
            utilisateur.setTelephone( utilisateurDTO.getTelephone() );
            utilisateur.setAdresse( utilisateurDTO.getAdresse() );
            utilisateur.setRole( utilisateurDTO.getRole() );
            utilisateur.setActif( utilisateurDTO.getActif() );

            return utilisateur;
        }
    }

    @Override
    public UtilisateurDtoResponse toDtoResponse(Utilisateur utilisateur) {
        if ( utilisateur == null ) {
            return null;
        }

        if (utilisateur instanceof Hopital) {
            return hopitalMapper.toDtoResponse( (Hopital) utilisateur );
        }
        else if (utilisateur instanceof Demandeur) {
            return demandeurMapper.toDtoResponse( (Demandeur) utilisateur );
        }
        else if (utilisateur instanceof Donneur) {
            return donneurMapper.toDtoResponse( (Donneur) utilisateur );
        }
        else {
            UtilisateurDtoResponse utilisateurDtoResponse = createDtoResponse( utilisateur );

            utilisateurDtoResponse.setId( utilisateur.getId() );
            utilisateurDtoResponse.setNom( utilisateur.getNom() );
            utilisateurDtoResponse.setPrenom( utilisateur.getPrenom() );
            utilisateurDtoResponse.setEmail( utilisateur.getEmail() );
            utilisateurDtoResponse.setTelephone( utilisateur.getTelephone() );
            utilisateurDtoResponse.setAdresse( utilisateur.getAdresse() );
            utilisateurDtoResponse.setRole( utilisateur.getRole() );
            utilisateurDtoResponse.setActif( utilisateur.getActif() );

            return utilisateurDtoResponse;
        }
    }
}

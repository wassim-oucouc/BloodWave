package org.example.bloodwave.application.mapper;


import org.example.bloodwave.application.dto.request.DemandeurDTO;
import org.example.bloodwave.application.dto.request.DonneurDTO;
import org.example.bloodwave.application.dto.request.HopitalDTO;
import org.example.bloodwave.application.dto.request.UtilisateurDTO;
import org.example.bloodwave.application.dto.response.DemandeurDtoResponse;
import org.example.bloodwave.application.dto.response.DonneurDtoResponse;
import org.example.bloodwave.application.dto.response.HopitalDtoResponse;
import org.example.bloodwave.application.dto.response.UtilisateurDtoResponse;
import org.example.bloodwave.domain.entity.Demandeur;
import org.example.bloodwave.domain.entity.Donneur;
import org.example.bloodwave.domain.entity.Hopital;
import org.example.bloodwave.domain.entity.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = "spring",uses = {DonneurMapper.class, DemandeurMapper.class, HopitalMapper.class})
public abstract class UtilisateurMapper {

    @SubclassMapping(source = HopitalDTO.class, target = Hopital.class)
    @SubclassMapping(source = DemandeurDTO.class, target = Demandeur.class)
    @SubclassMapping(source = DonneurDTO.class, target = Donneur.class)
    public abstract Utilisateur toEntity(UtilisateurDTO utilisateurDTO);
    // --------------------------
    // Map from Entity to DTO Response
    // --------------------------
    @SubclassMapping(source = Hopital.class, target = HopitalDtoResponse.class)
    @SubclassMapping(source = Demandeur.class, target = DemandeurDtoResponse.class)
    @SubclassMapping(source = Donneur.class, target = DonneurDtoResponse.class)
    public abstract UtilisateurDtoResponse toDtoResponse(Utilisateur utilisateur);


    @ObjectFactory
    protected UtilisateurDtoResponse createDtoResponse(Utilisateur utilisateur) {
        if (utilisateur instanceof Donneur) {
            return new DonneurDtoResponse();
        } else if (utilisateur instanceof Hopital) {
            return new HopitalDtoResponse();
        } else if (utilisateur instanceof Demandeur) {
            return new DemandeurDtoResponse();
        } else {
            throw new IllegalArgumentException(
                    "Unknown Utilisateur type: " + utilisateur.getClass().getSimpleName()
            );
        }
    }

        @ObjectFactory
        protected Utilisateur createEntity(UtilisateurDTO utilisateurDTO) {
            if (utilisateurDTO instanceof DonneurDTO) {
                return new Donneur();
            } else if (utilisateurDTO instanceof HopitalDTO) {
                return new Hopital();
            } else if (utilisateurDTO instanceof DemandeurDTO) {
                return new Demandeur();
            } else {
                throw new IllegalArgumentException(
                        "Unknown Utilisateur type: " + utilisateurDTO.getClass().getSimpleName()
                );
            }
    }
}

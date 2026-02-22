package org.example.bloodwave.application.mapper;

import javax.annotation.processing.Generated;
import org.example.bloodwave.application.dto.request.AdminDTO;
import org.example.bloodwave.application.dto.response.AdminDtoResponse;
import org.example.bloodwave.domain.entity.Admin;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-13T16:52:45+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class AdminMapperImpl extends AdminMapper {

    @Override
    public Admin toEntity(AdminDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Admin admin = new Admin();

        admin.setId( dto.getId() );
        admin.setNom( dto.getNom() );
        admin.setPrenom( dto.getPrenom() );
        admin.setEmail( dto.getEmail() );
        admin.setMotDePasse( dto.getMotDePasse() );
        admin.setTelephone( dto.getTelephone() );
        admin.setAdresse( dto.getAdresse() );
        admin.setRole( dto.getRole() );
        admin.setActif( dto.getActif() );

        return admin;
    }

    @Override
    public AdminDtoResponse toDtoResponse(Admin admin) {
        if ( admin == null ) {
            return null;
        }

        AdminDtoResponse adminDtoResponse = new AdminDtoResponse();

        return adminDtoResponse;
    }
}

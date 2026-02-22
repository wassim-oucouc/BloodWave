package org.example.bloodwave.application.mapper;


import org.example.bloodwave.application.dto.request.AdminDTO;
import org.example.bloodwave.application.dto.response.AdminDtoResponse;
import org.example.bloodwave.domain.entity.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AdminMapper {

    public abstract Admin toEntity(AdminDTO dto);

    public abstract AdminDtoResponse toDtoResponse(Admin admin);
}

package org.example.bloodwave.mapper;


import org.example.bloodwave.dto.request.AdminDTO;
import org.example.bloodwave.dto.response.AdminDtoResponse;
import org.example.bloodwave.entity.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AdminMapper {

    public abstract Admin toEntity(AdminDTO dto);

    public abstract AdminDtoResponse toDtoResponse(Admin admin);
}

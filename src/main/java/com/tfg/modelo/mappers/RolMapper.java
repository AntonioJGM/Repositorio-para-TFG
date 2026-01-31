package com.tfg.modelo.mappers;

import org.springframework.stereotype.Component;

import com.tfg.modelo.dtos.RolRequestDto;
import com.tfg.modelo.dtos.RolResponseDto;
import com.tfg.modelo.entities.Rol;
import com.tfg.modelo.entities.Usuario;

@Component
public class RolMapper {
	
	public RolResponseDto toResponseDto(Rol rol) {
        if (rol == null) return null;

        return RolResponseDto.builder()
                .idRol(rol.getIdRol())
                .nombre(rol.getNombre())
                .build();
    }

    public Usuario toEntity(RolRequestDto dto) {
        if (dto == null) return null;

        return Usuario.builder()
                .nombre(dto.getNombre())
                .build();
    }

}

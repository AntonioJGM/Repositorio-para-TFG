package com.tfg.modelo.mappers;

import org.springframework.stereotype.Component;

import com.tfg.modelo.dtos.UsuarioRequestDto;
import com.tfg.modelo.dtos.UsuarioResponseDto;
import com.tfg.modelo.entities.Usuario;

@Component
public class UsuarioMapper {
	
	public UsuarioResponseDto toResponseDto(Usuario usuario) {
        if (usuario == null) return null;

        return UsuarioResponseDto.builder()
                .usuarioId(usuario.getIdUsuario())
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .email(usuario.getEmail())
                .activo(usuario.isActivo())
                .rol(usuario.getRol().getNombre())
                .build();
    }

    public Usuario toEntity(UsuarioRequestDto dto) {
        if (dto == null) return null;

        return Usuario.builder()
                .nombre(dto.getNombre())
                .apellidos(dto.getApellidos())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }

}

package com.tfg.modelo.services;

import com.tfg.modelo.dtos.UsuarioRequestDto;
import com.tfg.modelo.dtos.UsuarioResponseDto;
import com.tfg.modelo.entities.Usuario;

public interface UsuarioService extends ICrudGenerico<Usuario, Integer>{
	
	UsuarioResponseDto toResponseDTO(Usuario usuario);
	Usuario fromRequestDTO(UsuarioRequestDto dto);

}

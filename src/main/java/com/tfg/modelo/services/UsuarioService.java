package com.tfg.modelo.services;

import java.util.List;

import com.tfg.modelo.dtos.LibroRequestDto;
import com.tfg.modelo.dtos.LibroResponseDto;
import com.tfg.modelo.dtos.UsuarioRequestDto;
import com.tfg.modelo.dtos.UsuarioResponseDto;
import com.tfg.modelo.entities.Usuario;

public interface UsuarioService{
	
	List<UsuarioResponseDto> findAll();
	UsuarioResponseDto findById(int id);
	UsuarioResponseDto create(UsuarioRequestDto dto);
	UsuarioResponseDto update(int id, UsuarioRequestDto dto);
    void delete(int id);
	
	

}

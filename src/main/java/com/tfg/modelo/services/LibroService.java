package com.tfg.modelo.services;

import java.util.List;

import com.tfg.modelo.dtos.LibroRequestDto;
import com.tfg.modelo.dtos.LibroResponseDto;

public interface LibroService {
	
	List<LibroResponseDto> findAll();
	LibroResponseDto findById(int id);
    LibroResponseDto create(LibroRequestDto dto);
    LibroResponseDto update(int id, LibroRequestDto dto);
    void delete(int id);
   
}

package com.tfg.modelo.services;

import java.util.List;

import com.tfg.modelo.dtos.PrestamoRequestDto;
import com.tfg.modelo.dtos.PrestamoResponseDto;


public interface PrestamoService{
	
	List<PrestamoResponseDto> findAll();
	PrestamoResponseDto findById(int id);
	PrestamoResponseDto create(PrestamoRequestDto dto);
	PrestamoResponseDto update(int id, PrestamoRequestDto dto);
    void delete(int id);

}

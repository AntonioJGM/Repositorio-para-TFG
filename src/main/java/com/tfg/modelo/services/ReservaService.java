package com.tfg.modelo.services;

import java.util.List;
import com.tfg.modelo.dtos.ReservaRequestDto;
import com.tfg.modelo.dtos.ReservaResponseDto;

public interface ReservaService{
	
	List<ReservaResponseDto> findAll();
	ReservaResponseDto findById(int id);
	ReservaResponseDto create(ReservaRequestDto dto);
	ReservaResponseDto update(int id, ReservaRequestDto dto);
    void delete(int id);

}

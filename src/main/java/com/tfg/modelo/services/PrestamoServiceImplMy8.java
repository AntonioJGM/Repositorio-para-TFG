package com.tfg.modelo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.modelo.dtos.PrestamoRequestDto;
import com.tfg.modelo.dtos.PrestamoResponseDto;
import com.tfg.modelo.entities.Prestamo;
import com.tfg.modelo.mappers.PrestamoMapper;
import com.tfg.modelo.repositories.PrestamoRepository;

@Service
public class PrestamoServiceImplMy8 implements PrestamoService{
	
	@Autowired
	private PrestamoRepository prestamoRepository;
	
	@Autowired
	private PrestamoMapper prestamoMapper;

	@Override
	public List<PrestamoResponseDto> findAll() {
		return prestamoRepository.findAll().stream()
				.map(prestamoMapper::toResponseDto)
				.toList();
	}

	@Override
	public PrestamoResponseDto findById(int id) {
		return prestamoMapper.toResponseDto(prestamoRepository.findById(id).orElse(null));
	}

	@Override
	public PrestamoResponseDto create(PrestamoRequestDto dto) {
		if (dto == null) {
			throw new IllegalArgumentException("El prestamo no puede ser null");
		}
		
		Prestamo nuevoPrestamo = prestamoMapper.toEntity(dto);
		
		Prestamo guardado = prestamoRepository.save(nuevoPrestamo);
		
		return prestamoMapper.toResponseDto(guardado);
	}

	@Override
	public PrestamoResponseDto update(int id, PrestamoRequestDto dto) {
		Prestamo prestamo = prestamoRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Prestamo no encontrado"));
		prestamo.setFechaInicio(dto.getFechaInicio());
		
		Prestamo actuializado = prestamoRepository.save(prestamo);
		return prestamoMapper.toResponseDto(actuializado);
	}

	@Override
	public void delete(int id) {
		if (!prestamoRepository.existsById(id)) {
			throw new IllegalArgumentException("Prestamo no encontrado");
		}
		prestamoRepository.deleteById(id);
		
	}
	

}

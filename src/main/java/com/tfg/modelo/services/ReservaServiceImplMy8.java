package com.tfg.modelo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.modelo.dtos.ReservaRequestDto;
import com.tfg.modelo.dtos.ReservaResponseDto;
import com.tfg.modelo.entities.Reserva;
import com.tfg.modelo.mappers.ReservaMapper;
import com.tfg.modelo.repositories.ReservaRepository;

@Service
public class ReservaServiceImplMy8 implements ReservaService{
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private ReservaMapper reservaMapper;
	
	
	@Override
	public List<ReservaResponseDto> findAll() {
		return reservaRepository.findAll().stream()
				.map(reservaMapper::toResponseDto)
				.toList();
	}
	
	@Override
	public ReservaResponseDto findById(int id) {
		return reservaMapper.toResponseDto(reservaRepository.findById(id).orElse(null));
	}

	

	@Override
	public ReservaResponseDto create(ReservaRequestDto dto) {
		
		if (dto == null) {
			throw new IllegalArgumentException("La reserva no puede ser null");
		}
		Reserva nuevaReserva = reservaMapper.toEntity(dto);
		Reserva guardado = reservaRepository.save(nuevaReserva);
		return reservaMapper.toResponseDto(guardado);
	}

	@Override
	public ReservaResponseDto update(int id, ReservaRequestDto dto) {
		Reserva reserva = reservaRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Reserva no encontrada"));
		
		reserva.setFechaReserva(dto.getFechaReserva());
		
		Reserva actualizado = reservaRepository.save(reserva);
		
		return reservaMapper.toResponseDto(actualizado);
	}

	@Override
	public void delete(int id) {
		if (!reservaRepository.existsById(id))
			throw new RuntimeException("Reserva no encontrada");
		reservaRepository.deleteById(id);		
	}
	
}

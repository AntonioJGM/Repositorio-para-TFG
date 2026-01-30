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
	public ReservaResponseDto insert(ReservaRequestDto dto) {
		
		if (dto == null) {
			throw new ;
		}
		return reservaRepository.save(entidad);
	}

	@Override
	public Reserva updateOne(Reserva entidad) {
		if (reservaRepository.existsById(entidad.getIdReserva())) {
			return reservaRepository.save(entidad);
		}
		return null;
	}

	@Override
	public void deleteOne(Integer atributoId) {
		reservaRepository.deleteById(atributoId);		
	}
	
}

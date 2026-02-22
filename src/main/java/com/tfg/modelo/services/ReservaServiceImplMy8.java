package com.tfg.modelo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.modelo.dtos.ReservaRequestDto;
import com.tfg.modelo.dtos.ReservaResponseDto;
import com.tfg.modelo.entities.Libro;
import com.tfg.modelo.entities.Reserva;
import com.tfg.modelo.entities.Usuario;
import com.tfg.modelo.mappers.ReservaMapper;
import com.tfg.modelo.repositories.LibroRepository;
import com.tfg.modelo.repositories.ReservaRepository;
import com.tfg.modelo.repositories.UsuarioRepository;

@Service
public class ReservaServiceImplMy8 implements ReservaService{
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private ReservaMapper reservaMapper;
	
	@Autowired 
	private LibroRepository libroRepository; 
	
	@Autowired 
	private UsuarioRepository usuarioRepository;
	
	
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
		
		// Buscar entidades relacionadas 
		Libro libro = libroRepository.findById(dto.getIdLibro()) 
				.orElseThrow(() -> new RuntimeException("Libro no encontrado")); 
		
		Usuario usuario = usuarioRepository.findById(dto.getIdUsuario()) 
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		
		//Conviertir DTO a entidad
		Reserva nuevaReserva = reservaMapper.toEntity(dto);
		
		//Asignar relaciones 
		nuevaReserva.setLibro(libro); 
		nuevaReserva.setUsuario(usuario);
		
		//Guarda
		Reserva guardado = reservaRepository.save(nuevaReserva);
		return reservaMapper.toResponseDto(guardado);
	}

	@Override
	public ReservaResponseDto update(int id, ReservaRequestDto dto) {
		Reserva reserva = reservaRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Reserva no encontrada"));
		
		//actualiza fecha
		reserva.setFechaReserva(dto.getFechaReserva());
		
		//Si viene idLibro, actualizarlo 
		if (dto.getIdLibro() != 0) { 
			Libro libro = libroRepository.findById(dto.getIdLibro()) 
					.orElseThrow(() -> new RuntimeException("Libro no encontrado")); 
			reserva.setLibro(libro); 
		}
		
		//Si viene idUsuario, actualizarlo 
		if (dto.getIdUsuario() != 0) { 
			Usuario usuario = usuarioRepository.findById(dto.getIdUsuario()) 
					.orElseThrow(() -> new RuntimeException("Usuario no encontrado")); 
			reserva.setUsuario(usuario); 
		}
		
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

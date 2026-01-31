package com.tfg.modelo.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.modelo.dtos.PrestamoRequestDto;
import com.tfg.modelo.dtos.PrestamoResponseDto;
import com.tfg.modelo.entities.Libro;
import com.tfg.modelo.entities.Prestamo;
import com.tfg.modelo.entities.Usuario;
import com.tfg.modelo.mappers.PrestamoMapper;
import com.tfg.modelo.repositories.LibroRepository;
import com.tfg.modelo.repositories.PrestamoRepository;
import com.tfg.modelo.repositories.UsuarioRepository;

@Service
public class PrestamoServiceImplMy8 implements PrestamoService{
	
	@Autowired
	private PrestamoRepository prestamoRepository;
	
	@Autowired
	private PrestamoMapper prestamoMapper;
	
	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	@Autowired
	private LibroRepository libroRepository; 

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

	/*
	@Override
	public PrestamoResponseDto create(PrestamoRequestDto dto) {
		if (dto == null) {
			throw new IllegalArgumentException("El prestamo no puede ser null");
		}
		
		Prestamo nuevoPrestamo = prestamoMapper.toEntity(dto);
		
		Prestamo guardado = prestamoRepository.save(nuevoPrestamo);
		
		return prestamoMapper.toResponseDto(guardado);
	}*/
	
	@Override
	public PrestamoResponseDto create(PrestamoRequestDto dto) {
	    if (dto == null) {
	        throw new IllegalArgumentException("El prestamo no puede ser null");
	    }

	    Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()) 
	    		.orElseThrow(() -> new RuntimeException("Usuario no encontrado")); 
	    
	    Libro libro = libroRepository.findById(dto.getLibroId()) 
	    		.orElseThrow(() -> new RuntimeException("Libro no encontrado"));
	    
	    Prestamo nuevoPrestamo = prestamoMapper.toEntity(dto);
	    
	    //Libro y Ususario son obligatorios
	    nuevoPrestamo.setUsuario(usuario); 
	    nuevoPrestamo.setLibro(libro);
	    
	    //cuando se crea el préstamo automáticamente se crea la fecha de fin, solo pueden tener el libro 20 días máximo
	    nuevoPrestamo.setFechaFin(dto.getFechaInicio().plusDays(20));

	    nuevoPrestamo.setFechaDevolucion(null);
	    nuevoPrestamo.setDiasRetraso(0);
	    nuevoPrestamo.setImporteSancion(BigDecimal.valueOf(0.0));

	    Prestamo guardado = prestamoRepository.save(nuevoPrestamo);

	    return prestamoMapper.toResponseDto(guardado);
	}


	@Override
	public PrestamoResponseDto update(int id, PrestamoRequestDto dto) {
		Prestamo prestamo = prestamoRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Prestamo no encontrado"));
		
		prestamo.setFechaInicio(dto.getFechaInicio());
		
		//recalcula fecha fin automáticamente 
		prestamo.setFechaFin(dto.getFechaInicio().plusDays(20));
		
		// damos permiso para cambiar usuario o libro  
		Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()) 
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado")); 
		
		Libro libro = libroRepository.findById(dto.getLibroId()) 
				.orElseThrow(() -> new RuntimeException("Libro no encontrado")); 
		
		prestamo.setUsuario(usuario); 
		prestamo.setLibro(libro);
		
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

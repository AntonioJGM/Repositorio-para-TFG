package com.tfg.modelo.services;

import java.math.BigDecimal;
import java.time.LocalDate;
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
	public PrestamoResponseDto findById(int idPrestamo) {
		return prestamoMapper.toResponseDto(prestamoRepository.findById(idPrestamo).orElse(null));
	}
	
	@Override
	public PrestamoResponseDto create(PrestamoRequestDto dto) {
	    if (dto == null) {
	        throw new IllegalArgumentException("El prestamo no puede ser null");
	    }

	    Usuario usuario = usuarioRepository.findById(dto.getIdUsuario()) 
	    		.orElseThrow(() -> new RuntimeException("Usuario no encontrado")); 
	    
	    Libro libro = libroRepository.findById(dto.getIdLibro()) 
	    		.orElseThrow(() -> new RuntimeException("Libro no encontrado"));
	    
	    //Comprobamos que el libro esta disponible
	    if (!libro.isDisponible()) { 
	    	throw new RuntimeException("El libro no está disponible para préstamo");
	    }
	    
	    Prestamo nuevoPrestamo = prestamoMapper.toEntity(dto);
	    
	    //Libro y Ususario son obligatorios
	    nuevoPrestamo.setUsuario(usuario); 
	    nuevoPrestamo.setLibro(libro);
	    
	    //cuando se crea el préstamo automáticamente se crea la fecha de fin, solo pueden tener el libro 20 días máximo
	    nuevoPrestamo.setFechaFin(dto.getFechaInicio().plusDays(20));

	    nuevoPrestamo.setFechaDevolucion(null);
	    nuevoPrestamo.setDiasRetraso(0);
	    nuevoPrestamo.setImporteSancion(BigDecimal.valueOf(0.0));
	    
	    //guardamos libro como no disponible 
	    libro.setDisponible(false); 
	    libroRepository.save(libro);
	    
	    Prestamo guardado = prestamoRepository.save(nuevoPrestamo);

	    return prestamoMapper.toResponseDto(guardado);
	}


	@Override
	public PrestamoResponseDto update(int id, PrestamoRequestDto dto) {
		Prestamo prestamo = prestamoRepository.findById(id) .orElseThrow(() -> new RuntimeException("Prestamo no encontrado")); 
		
		// Actualizar fecha de inicio 
		prestamo.setFechaInicio(dto.getFechaInicio()); 
		
		// Recalcular fecha fin automáticamente (20 días después) 
		prestamo.setFechaFin(dto.getFechaInicio().plusDays(20)); 
		
		// Permitir cambiar usuario 
		Usuario usuario = usuarioRepository.findById(dto.getIdUsuario()) .orElseThrow(() -> new RuntimeException("Usuario no encontrado")); 
		prestamo.setUsuario(usuario); 
		
		// Permitir cambiar libro 
		Libro libro = libroRepository.findById(dto.getIdLibro()) .orElseThrow(() -> new RuntimeException("Libro no encontrado")); 
		prestamo.setLibro(libro); Prestamo actualizado = prestamoRepository.save(prestamo); 
		
		return prestamoMapper.toResponseDto(actualizado);
	}

	@Override
	public void delete(int id) {
		if (!prestamoRepository.existsById(id)) {
			throw new IllegalArgumentException("Prestamo no encontrado");
		}
		prestamoRepository.deleteById(id);
		
	}

	@Override
	public void devolverPrestamo(int idPrestamo, int idUsuario) {
		Prestamo prestamo = prestamoRepository.findById(idPrestamo) 
				.orElseThrow(() -> new RuntimeException("Préstamo no encontrado")); 
		
		if (prestamo.getUsuario().getIdUsuario() != idUsuario) { 
			throw new RuntimeException("No puedes devolver un préstamo que no es tuyo"); 
			} 
		
		prestamo.setFechaDevolucion(LocalDate.now()); 
		
		Libro libro = prestamo.getLibro(); 
		libro.setDisponible(true); 
		
		libroRepository.save(libro); 
		prestamoRepository.save(prestamo);
		
	}

	@Override
	public List<PrestamoResponseDto> obtenerPrestamosActivosUsuario(int idUsuario) {
		return prestamoRepository.findByUsuarioIdUsuarioAndFechaDevolucionIsNull(idUsuario).stream()
					.map(prestamoMapper::toResponseDto)
					.toList();
	}

	@Override
	public void obtenerPorIdPrestamosAdIdUsuario(int idPrestamo, int idUsuario) {
		Prestamo prestamo = prestamoRepository
	            .findByIdPrestamoAndUsuarioIdUsuario(idPrestamo, idUsuario)
	            .orElseThrow(() -> new RuntimeException("No puedes devolver un préstamo que no es tuyo"));

	    prestamo.setFechaDevolucion(LocalDate.now());

	    Libro libro = prestamo.getLibro();
	    libro.setDisponible(true);

	    libroRepository.save(libro);
	    prestamoRepository.save(prestamo);
	}
	

}

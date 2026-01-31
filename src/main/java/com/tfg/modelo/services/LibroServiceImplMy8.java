package com.tfg.modelo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.modelo.dtos.LibroRequestDto;
import com.tfg.modelo.dtos.LibroResponseDto;
import com.tfg.modelo.entities.Libro;
import com.tfg.modelo.mappers.LibroMapper;
import com.tfg.modelo.repositories.LibroRepository;

@Service
public class LibroServiceImplMy8 implements LibroService{
	
	@Autowired
	private LibroRepository libroRepository;
	
	@Autowired
	private LibroMapper libroMapper;
	
	@Override
	public List<LibroResponseDto> findAll() {
		return libroRepository.findAll().stream()
				.map(libroMapper::toResponseDto)
				.toList();
	}

	@Override
	public LibroResponseDto findById(int idLibro) {
		return libroMapper.toResponseDto(libroRepository.findById(idLibro).orElse(null));
	}

	@Override
	public LibroResponseDto create(LibroRequestDto libroDto) {
		
		if (libroDto == null) {
			throw new IllegalArgumentException("El libro no puede ser null");
		}
		
		Libro nuevoLibro = libroMapper.toEntity(libroDto);
		
		Libro guardado =libroRepository.save(nuevoLibro);
		
		return libroMapper.toResponseDto(guardado);
	}

	@Override
	public LibroResponseDto update(int idLibro, LibroRequestDto libroDto) {
		Libro libro = libroRepository.findById(idLibro)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        libro.setTitulo(libroDto.getTitulo());
        libro.setAutor(libroDto.getAutor());
        libro.setEditorial(libroDto.getEditorial());
        libro.setCategoria(libroDto.getCategoria());
        libro.setIsbn(libroDto.getIsbn());
        libro.setDisponible(libroDto.isDisponible());
        libro.setImagen(libroDto.getImagen());

        Libro actualizado = libroRepository.save(libro);
        return libroMapper.toResponseDto(actualizado);
	}

	@Override
	public void delete(int idLibro) {

		if (!libroRepository.existsById(idLibro)) {
            throw new RuntimeException("Libro no encontrado");
        }
        libroRepository.deleteById(idLibro);
		
	}
	

}

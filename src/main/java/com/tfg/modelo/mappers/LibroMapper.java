package com.tfg.modelo.mappers;

import org.springframework.stereotype.Component;

import com.tfg.modelo.dtos.LibroRequestDto;
import com.tfg.modelo.dtos.LibroResponseDto;
import com.tfg.modelo.entities.Libro;

@Component
public class LibroMapper {
	
	public Libro toEntity(LibroRequestDto libroDto) {
        if (libroDto == null) return null;

        return Libro.builder()
                .titulo(libroDto.getTitulo())
                .autor(libroDto.getAutor())
                .editorial(libroDto.getEditorial())
                .categoria(libroDto.getCategoria())
                .isbn(libroDto.getIsbn())
                .disponible(libroDto.isDisponible())
                .imagen(libroDto.getImagen())
                .build();
    }

    public LibroResponseDto toResponseDto(Libro libro) {
        if (libro == null) 
        	return null;

        return LibroResponseDto.builder()
                .idLibro(libro.getIdLibro())
                .titulo(libro.getTitulo())
                .autor(libro.getAutor())
                .editorial(libro.getEditorial())
                .categoria(libro.getCategoria())
                .isbn(libro.getIsbn())
                .disponible(libro.isDisponible())
                .imagen(libro.getImagen())
                .build();
    }

}

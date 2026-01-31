package com.tfg.modelo.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LibroResponseDto {
	
	private int idLibro;
	private String titulo;
	private String autor;
	private String editorial;
	private String categoria;
	private String isbn;
	private boolean disponible;
	private String imagen;

}

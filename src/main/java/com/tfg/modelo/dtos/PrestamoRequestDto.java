package com.tfg.modelo.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PrestamoRequestDto {
	
	private LocalDate fechaInicio;
	private int usuarioId;
	private int libroId;

}

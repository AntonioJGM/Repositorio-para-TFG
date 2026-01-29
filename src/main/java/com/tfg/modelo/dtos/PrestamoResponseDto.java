package com.tfg.modelo.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PrestamoResponseDto {
	
	private int idPrestamo;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private LocalDate fechaDevolucion;
	private int diasRetraso;
	private BigDecimal importeSancion;
	private String nombreUsuario;
	private String tituloLibro;

}

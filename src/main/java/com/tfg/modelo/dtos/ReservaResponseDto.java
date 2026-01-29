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
public class ReservaResponseDto {
	
	private int idReserva;
	private LocalDate fechaReserva;
	private boolean activa;
	private String nombreUsuario;
	private String tituloLibro;

}

package com.tfg.modelo.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UsuarioRequestDto {
	
	private String nombre;
	private String apellidos;
	private String email;
	private String password;
	private String rol;

}

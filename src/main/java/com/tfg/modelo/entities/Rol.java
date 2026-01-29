package com.tfg.modelo.entities;

import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name="roles")
public class Rol implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_rol")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRol;
	private String nombre;

}

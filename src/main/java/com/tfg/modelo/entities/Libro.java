package com.tfg.modelo.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="libros")
public class Libro implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_libro")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idLibro;
	private String titulo;
	private String autor;
	private String editorial;
	private String categoria;
	private String isbn;
	private boolean disponible;
	private String imagen;
}

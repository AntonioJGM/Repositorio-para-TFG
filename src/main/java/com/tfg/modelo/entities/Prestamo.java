package com.tfg.modelo.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name="prestamos")
public class Prestamo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_prestamo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPrestamo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicio")
	private LocalDate fechaInicio;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_fin")
	private LocalDate fechaFin;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_devolucion")
	private LocalDate fechaDevolucion;
	
	@Column(name="dias_retraso")
	private int diasRetraso;
	
	@Column(name="importe_sancion")
	private BigDecimal importeSancion;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="id_libro")
	private Libro libro;
}

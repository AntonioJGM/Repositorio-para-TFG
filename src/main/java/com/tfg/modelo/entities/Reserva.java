package com.tfg.modelo.entities;

import java.io.Serializable;
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
@Table(name="reservas")
public class Reserva implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_reserva")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idReserva;
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_reserva")
	private LocalDate fechaReserva;
	private boolean activa;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="id_libro")
	private Libro libro;

}

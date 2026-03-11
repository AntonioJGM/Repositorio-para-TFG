package com.tfg.modelo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.modelo.entities.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer>{

		//Obtener las reservas del usuario
		List<Reserva> findByUsuario_IdUsuario(int idUsuario);
	

	
}

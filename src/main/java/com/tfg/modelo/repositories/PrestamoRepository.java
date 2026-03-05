package com.tfg.modelo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.modelo.entities.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer>{

	//Obtener los préstamos activos del usuario
	List<Prestamo> findByUsuarioIdUsuarioAndFechaDevolucionIsNull(int idUsuario);
	
	//Obtenemos por idPrestamos y idUsuario para el endpoint de las devoluciones
	Optional<Prestamo> findByIdPrestamoAndUsuarioIdUsuario(int idPrestamo, int idUsuario);

	
}

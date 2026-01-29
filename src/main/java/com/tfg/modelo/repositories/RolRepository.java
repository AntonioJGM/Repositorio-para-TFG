package com.tfg.modelo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.modelo.entities.Rol;



public interface RolRepository extends JpaRepository<Rol, Integer>{
	
	Optional<Rol> findByNombre(String nombre);

}

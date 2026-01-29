package com.tfg.modelo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.modelo.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	Optional<Usuario> findByEmail(String email);

}

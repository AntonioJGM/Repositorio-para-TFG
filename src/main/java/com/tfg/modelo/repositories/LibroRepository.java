package com.tfg.modelo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.modelo.entities.Libro;

public interface LibroRepository extends JpaRepository<Libro, Integer> {

}

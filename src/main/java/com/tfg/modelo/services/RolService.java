package com.tfg.modelo.services;

import java.util.List;

import com.tfg.modelo.dtos.RolRequestDto;
import com.tfg.modelo.entities.Rol;

public interface RolService{
	
	List<Rol> findAll();
	Rol findById(int idRol);
	Rol create(String rol);
	Rol update(int idRol, RolRequestDto dto);
	void delete(int idRol);

}

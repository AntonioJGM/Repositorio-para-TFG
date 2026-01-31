package com.tfg.modelo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.modelo.dtos.RolRequestDto;
import com.tfg.modelo.entities.Rol;
import com.tfg.modelo.repositories.RolRepository;


@Service
public class RolServiceImplMy8 implements RolService{
	
	@Autowired
	private RolRepository rolRepository;

	@Override
	public Rol findById(int idRol) {
		return rolRepository.findById(idRol).orElseThrow(() -> new RuntimeException("Rol no encontrado"));
	}

	@Override
	public List<Rol> findAll() {
		return rolRepository.findAll();
	}

	@Override
	public Rol create(String rol) {
		if (rol == null) {
			throw new IllegalArgumentException("El rol no puede ser null");
		}
		
		Rol nuevoRol = new Rol();
		nuevoRol.setNombre(rol);
		
		return rolRepository.save(nuevoRol);
	}

	@Override
	public Rol update(int idRol, RolRequestDto dto) {
		Rol nuevoRol = rolRepository.findById(idRol)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
		
		if (dto.getNombre() == null) {
			throw new IllegalArgumentException("El rol no puede ser null");
		}
		
		nuevoRol.setNombre(dto.getNombre().toUpperCase());
		
		return rolRepository.save(nuevoRol);
	}

	@Override
	public void delete(int idRol) {
		rolRepository.findById(idRol).orElseThrow(() -> new RuntimeException("Rol no encontrado"));
		
		rolRepository.deleteById(idRol);
	}

}

package com.tfg.modelo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.modelo.entities.Prestamo;
import com.tfg.modelo.repositories.PrestamoRepository;

@Service
public class PrestamoServiceImplMy8 implements PrestamoService{
	
	@Autowired
	private PrestamoRepository prestamoRepository;

	@Override
	public Prestamo findById(Integer atributoId) {
		return prestamoRepository.findById(atributoId).orElse(null);
	}

	@Override
	public List<Prestamo> findAll() {
		return prestamoRepository.findAll();
	}

	@Override
	public Prestamo insertOne(Prestamo entidad) {
		if (entidad == null || !prestamoRepository.existsById(entidad.getIdPrestamo())) {
			return prestamoRepository.save(entidad);
		}
		return null;
	}

	@Override
	public Prestamo updateOne(Prestamo entidad) {
		if (prestamoRepository.existsById(entidad.getIdPrestamo())) {
			return prestamoRepository.save(entidad);
		}
		return null;
	}

	@Override
	public void deleteOne(Integer atributoId) {
		prestamoRepository.deleteById(atributoId);
	}
	

}

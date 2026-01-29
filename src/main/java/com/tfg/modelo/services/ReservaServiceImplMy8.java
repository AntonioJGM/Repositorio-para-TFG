package com.tfg.modelo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.modelo.entities.Reserva;
import com.tfg.modelo.repositories.ReservaRepository;

@Service
public class ReservaServiceImplMy8 implements ReservaService{
	
	@Autowired
	private ReservaRepository reservaRepository;

	@Override
	public Reserva findById(Integer atributoId) {
		return reservaRepository.findById(atributoId).orElse(null);
	}

	@Override
	public List<Reserva> findAll() {
		return reservaRepository.findAll();
	}

	@Override
	public Reserva insertOne(Reserva entidad) {
		
		if (entidad == null || !reservaRepository.existsById(entidad.getIdReserva())) {
			return reservaRepository.save(entidad);
		}
		return null;
	}

	@Override
	public Reserva updateOne(Reserva entidad) {
		if (reservaRepository.existsById(entidad.getIdReserva())) {
			return reservaRepository.save(entidad);
		}
		return null;
	}

	@Override
	public void deleteOne(Integer atributoId) {
		reservaRepository.deleteById(atributoId);		
	}
	
}

package com.tfg.modelo.mappers;

import org.springframework.stereotype.Component;

import com.tfg.modelo.dtos.ReservaRequestDto;
import com.tfg.modelo.dtos.ReservaResponseDto;
import com.tfg.modelo.entities.Reserva;

@Component
public class ReservaMapper {
	
	public ReservaResponseDto toResponseDto(Reserva reserva) {
        if (reserva == null) return null;

        return ReservaResponseDto.builder()
                .idReserva(reserva.getIdReserva())
                .fechaReserva(reserva.getFechaReserva())
                .activa(reserva.isActiva())
                .nombreUsuario(reserva.getUsuario().getNombre())
                .tituloLibro(reserva.getLibro().getTitulo())
                .build();
    }

    public Reserva toEntity(ReservaRequestDto dto) {
        if (dto == null) return null;

        return Reserva.builder()
                .fechaReserva(dto.getFechaReserva())
                .activa(true)
                .build();
    }

}

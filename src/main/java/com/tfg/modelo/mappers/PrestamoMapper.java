package com.tfg.modelo.mappers;

import org.springframework.stereotype.Component;

import com.tfg.modelo.dtos.PrestamoRequestDto;
import com.tfg.modelo.dtos.PrestamoResponseDto;
import com.tfg.modelo.entities.Prestamo;

@Component
public class PrestamoMapper {
	
	public PrestamoResponseDto toResponseDto(Prestamo prestamo) {
        if (prestamo == null) return null;

        return PrestamoResponseDto.builder()
                .idPrestamo(prestamo.getIdPrestamo())
                .fechaInicio(prestamo.getFechaInicio())
                .fechaFin(prestamo.getFechaFin())
                .fechaDevolucion(prestamo.getFechaDevolucion())
                .diasRetraso(prestamo.getDiasRetraso())
                .importeSancion(prestamo.getImporteSancion())
                .nombreUsuario(prestamo.getUsuario().getNombre())
                .tituloLibro(prestamo.getLibro().getTitulo())
                .build();
    }

    public Prestamo toEntity(PrestamoRequestDto dto) {
        if (dto == null) return null;

        return Prestamo.builder()
                .fechaInicio(dto.getFechaInicio())
                .build();
    }

}

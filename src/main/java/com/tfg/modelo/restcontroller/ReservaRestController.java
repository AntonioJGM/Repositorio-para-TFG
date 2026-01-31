package com.tfg.modelo.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.modelo.dtos.ReservaRequestDto;
import com.tfg.modelo.dtos.ReservaResponseDto;
import com.tfg.modelo.services.ReservaService;

@RestController
@RequestMapping("/reserva")
public class ReservaRestController {
	
	@Autowired
    private ReservaService reservaService;

    @GetMapping("/byId/{reservaId}")
    ResponseEntity<?> findOne(@PathVariable int reservaId) {
        ReservaResponseDto reserva = reservaService.findById(reservaId);

        if (reserva == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(reserva);
    }

    @GetMapping("/all")
    ResponseEntity<?> findAll() {
        return ResponseEntity.ok(reservaService.findAll());
    }

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody ReservaRequestDto reservaDto) {
    	
        ReservaResponseDto creada = reservaService.create(reservaDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/update/{reservaId}")
    ResponseEntity<?> update(@PathVariable int reservaId,
    		@RequestBody ReservaRequestDto reservaDto) {

        ReservaResponseDto actualizada = reservaService.update(reservaId, reservaDto);

        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/delete/{reservaId}")
    ResponseEntity<?> delete(@PathVariable int reservaId) {
        reservaService.delete(reservaId);
        return ResponseEntity.noContent().build();
    }
    
}

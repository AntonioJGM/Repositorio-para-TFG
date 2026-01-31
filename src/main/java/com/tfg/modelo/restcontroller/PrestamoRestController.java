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

import com.tfg.modelo.dtos.PrestamoRequestDto;
import com.tfg.modelo.dtos.PrestamoResponseDto;
import com.tfg.modelo.services.PrestamoService;

@RestController
@RequestMapping("/prestamo")
public class PrestamoRestController {
	
	@Autowired
	private PrestamoService prestamoService;
	
	@GetMapping("/byId/{prestamoId}")
    ResponseEntity<?> findOne(@PathVariable int prestamoId) {
        PrestamoResponseDto prestamo = prestamoService.findById(prestamoId);

        if (prestamo == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(prestamo);
    }

    @GetMapping("/all")
    ResponseEntity<?> findAll() {
        return ResponseEntity.ok(prestamoService.findAll());
    }

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody PrestamoRequestDto prestamoDto) {
        PrestamoResponseDto creado = prestamoService.create(prestamoDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/update/{prestamoId}")
    ResponseEntity<?> update(@PathVariable int prestamoId,
    		@RequestBody PrestamoRequestDto prestamoDto) {

        PrestamoResponseDto actualizado = prestamoService.update(prestamoId, prestamoDto);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/delete/{prestamoId}")
    ResponseEntity<?> delete(@PathVariable int prestamoId) {
        prestamoService.delete(prestamoId);
        return ResponseEntity.noContent().build();
    }
}

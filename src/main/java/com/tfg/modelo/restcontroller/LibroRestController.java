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

import com.tfg.modelo.dtos.LibroRequestDto;
import com.tfg.modelo.dtos.LibroResponseDto;
import com.tfg.modelo.services.LibroService;

@RestController
@RequestMapping("/libro")
public class LibroRestController {
	
	@Autowired
	private LibroService libroService;
	
	@GetMapping("/byId/{libroId}")
    ResponseEntity<?> findOne(@PathVariable int libroId) {
        LibroResponseDto libro = libroService.findById(libroId);

        if (libro == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(libro);
    }

    @GetMapping("/all")
    ResponseEntity<?> findAll() {
        return ResponseEntity.ok(libroService.findAll());
    }

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody LibroRequestDto libroDto) {
        LibroResponseDto creado = libroService.create(libroDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/update/{libroId}")
    ResponseEntity<?> update(@PathVariable int libroId,
    		@RequestBody LibroRequestDto libroDto) {

        LibroResponseDto actualizado = libroService.update(libroId, libroDto);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/delete/{libroId}")
    ResponseEntity<?> delete(@PathVariable int libroId) {
        libroService.delete(libroId);
        return ResponseEntity.noContent().build();
    }
}
package com.tfg.modelo.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.modelo.dtos.RolRequestDto;
import com.tfg.modelo.entities.Rol;
import com.tfg.modelo.services.RolService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/rol")
public class RolRestController {
	
	@Autowired
	private RolService rolService;
	
	@GetMapping("/findById/{rolId}")
	ResponseEntity<?> findById(@PathVariable int rolId) {
		Rol rol = rolService.findById(rolId);

        if (rol == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(rol);
	}
	
	@GetMapping("/all")
	ResponseEntity<?> findAll() {
		return ResponseEntity.ok(rolService.findAll());
	}
	
	@PostMapping("/create/{rol}")
	ResponseEntity<?> create(@PathVariable String rol) {
		return ResponseEntity.status(HttpStatus.CREATED).body(rolService.create(rol));
	}
	
	@PutMapping("/update/{rolId}")
	ResponseEntity<?> update(@PathVariable int rolId,
			@RequestBody RolRequestDto dto) {
		Rol actualizado = rolService.update(rolId, dto);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);
	}
	
	@DeleteMapping("/delete/{rolId}")
	ResponseEntity<?> delete(@PathVariable int rolId) {
		rolService.delete(rolId);
        return ResponseEntity.noContent().build();
	}

}

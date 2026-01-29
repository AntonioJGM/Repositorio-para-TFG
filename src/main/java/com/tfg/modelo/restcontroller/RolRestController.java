package com.tfg.modelo.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.modelo.entities.Rol;
import com.tfg.modelo.services.RolService;

@RestController
@RequestMapping("/rol")
public class RolRestController {
	
	@Autowired
	private RolService rolService;
	
	@GetMapping("/findById/{rolId}")
	public Rol findById(@PathVariable int rolId) {
		return rolService.findById(rolId);
	}
	
	@GetMapping("/all")
	public List<Rol> findAll() {
		return rolService.findAll();
	}
	
	@PostMapping("/create/{rol}")
	public Rol create(@PathVariable String rol) {
		return rolService.create(rol);
	}
	
	@PutMapping("/update/{rolId}{nombreRol}")
	public Rol update(@PathVariable int rolId,
			@PathVariable String nombreRol) {
		return rolService.update(rolId,nombreRol);
	}
	
	@DeleteMapping("/delete/{rolId}")
	public void delete(@PathVariable int rolId) {
		rolService.delete(rolId);
	}

}

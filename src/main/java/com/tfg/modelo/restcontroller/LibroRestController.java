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

import com.tfg.modelo.dtos.LibroRequestDto;
import com.tfg.modelo.dtos.LibroResponseDto;
import com.tfg.modelo.dtos.UsuarioRequestDto;
import com.tfg.modelo.entities.Libro;
import com.tfg.modelo.entities.Usuario;
import com.tfg.modelo.services.LibroService;

@RestController
@RequestMapping("/libro")
public class LibroRestController {
	
	@Autowired
	private LibroService libroService;
	
	@GetMapping("/byId/{libroId}")
	public LibroResponseDto findOne(@PathVariable int libroId) {
		return libroService.findById(libroId);
	}
	
	@GetMapping("/all")
	public List<LibroResponseDto> findAll() {
		return libroService.findAll();
	}
	
	@PostMapping("/create")
	public LibroResponseDto create(@RequestBody LibroRequestDto libroDto) {
		return libroService.create(libroDto);
	}
	
	@PutMapping("/update/{libroId}")
	public LibroResponseDto update(@PathVariable int libroId,
			@RequestBody LibroRequestDto libroDto) {
		
		return libroService.update(libroId, libroDto);
	}
	
	@DeleteMapping("/delete/{libroId}")
	public void delete(@PathVariable int libroId) {
		libroService.delete(libroId);
	}

}

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

import com.tfg.modelo.dtos.UsuarioRequestDto;
import com.tfg.modelo.entities.Usuario;
import com.tfg.modelo.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/byId/{usuarioId}")
	public Usuario findOne(@PathVariable int usuarioId) {
		return usuarioService.findById(usuarioId);
	}
	
	@GetMapping("/all")
	public List<Usuario> findAll() {
		return usuarioService.findAll();
	}
	
	@PostMapping("/create")
	public Usuario create(@RequestBody UsuarioRequestDto usuarioDto) {
		return usuarioService.insertOne(usuarioService.fromRequestDTO(usuarioDto));
	}
	
	@PutMapping("/update/{usuarioId}")
	public Usuario update(@PathVariable int usuarioId,
			@RequestBody UsuarioRequestDto usuarioDto) {
		
		return usuarioService.insertOne(usuarioService.fromRequestDTO(usuarioDto));
	}
	
	@DeleteMapping("/delete/{usuarioId}")
	public void delete(@PathVariable int usuarioId) {
		usuarioService.deleteOne(usuarioId);
	}
	

}

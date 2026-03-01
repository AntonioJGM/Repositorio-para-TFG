package com.tfg.modelo.restcontroller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.modelo.dtos.UsuarioRequestDto;
import com.tfg.modelo.dtos.UsuarioResponseDto;
import com.tfg.modelo.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	//Con esto le decimos angular que rol tiene el ususario logueado para los permisos 
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication auth) {

        Map<String, Object> data = new HashMap<>();
        data.put("email", auth.getName());
        data.put("roles", auth.getAuthorities());

        return ResponseEntity.ok(data);
    }
	

	@GetMapping("/byId/{usuarioId}")
    ResponseEntity<?> findOne(@PathVariable int usuarioId) {
        UsuarioResponseDto usuario = usuarioService.findById(usuarioId);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/all")
    ResponseEntity<?> findAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody UsuarioRequestDto usuarioDto) {
        UsuarioResponseDto creado = usuarioService.create(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/update/{idUsuario}")
    ResponseEntity<?> update(@PathVariable int idUsuario, 
    		@RequestBody UsuarioRequestDto usuarioDto) {

        UsuarioResponseDto actualizado = usuarioService.update(idUsuario, usuarioDto);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/delete/{usuarioId}")
    ResponseEntity<?> delete(@PathVariable int usuarioId) {
        usuarioService.delete(usuarioId);
        return ResponseEntity.noContent().build();
    }
}

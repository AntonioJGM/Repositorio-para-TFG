package com.tfg.modelo.restcontroller;


import java.util.List;

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
import com.tfg.modelo.entities.Usuario;
import com.tfg.modelo.security.JwtService;
import com.tfg.modelo.services.PrestamoService;
import com.tfg.modelo.services.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/prestamo")
public class PrestamoRestController {
	
	@Autowired
	private PrestamoService prestamoService;
	
	@Autowired
	private UsuarioService usuarioService; 
	
	@Autowired
	private JwtService jwtService;
	
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

    @GetMapping("/mis-prestamos")
    ResponseEntity<?> misPrestamos(HttpServletRequest request){
    	try { 
    		String token = jwtService.extractToken(request); 
    		String email = jwtService.extractUsername(token); 
    		
    		Usuario usuario = usuarioService.findByEmail(email)
    				.orElseThrow(() -> new RuntimeException("Usuario no encontrado")); 
    		
    		List<PrestamoResponseDto> prestamos = 
    				prestamoService.obtenerPrestamosActivosUsuario(usuario.getIdUsuario()); 
    		
    		return ResponseEntity.ok(prestamos); 
    	} catch (RuntimeException ex) { 
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()); 
    	} catch (Exception ex) { 
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) .body("Error al obtener los préstamos del usuario"); 
    	}
    }
    
    @PutMapping("/devolver/{idPrestamo}")
    public ResponseEntity<?> devolverPrestamo( @PathVariable int idPrestamo, HttpServletRequest request) {

        try {
            String token = jwtService.extractToken(request);
            String email = jwtService.extractUsername(token);

            Usuario usuario = usuarioService.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            prestamoService.devolverPrestamo(idPrestamo, usuario.getIdUsuario());

            return ResponseEntity.ok("Devolución correcta");

        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al devolver el préstamo");
        }
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

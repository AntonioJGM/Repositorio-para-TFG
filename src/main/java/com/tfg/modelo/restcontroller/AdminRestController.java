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
import com.tfg.modelo.dtos.PrestamoRequestDto;
import com.tfg.modelo.dtos.PrestamoResponseDto;
import com.tfg.modelo.dtos.ReservaRequestDto;
import com.tfg.modelo.dtos.ReservaResponseDto;
import com.tfg.modelo.dtos.RolRequestDto;
import com.tfg.modelo.dtos.UsuarioRequestDto;
import com.tfg.modelo.dtos.UsuarioResponseDto;
import com.tfg.modelo.entities.Rol;
import com.tfg.modelo.services.LibroService;
import com.tfg.modelo.services.PrestamoService;
import com.tfg.modelo.services.ReservaService;
import com.tfg.modelo.services.RolService;
import com.tfg.modelo.services.UsuarioService;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
	
	@Autowired
    private LibroService libroService;

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private PrestamoService prestamoService; 
    
    @Autowired
    private ReservaService reservaService;
    
    @Autowired
    private RolService rolService; 
    
    
    //Mostrar libros
    @GetMapping("/libro/all")
    ResponseEntity<?> findAllLibros() {
        return ResponseEntity.ok(libroService.findAll());
    }
    
    // Crear libro
    @PostMapping("/libro/create")
    ResponseEntity<?> create(@RequestBody LibroRequestDto libroDto) {
        LibroResponseDto creado = libroService.create(libroDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
    
    //Actualizar libro
    @PutMapping("/libro/update/{libroId}")
    ResponseEntity<?> update(@PathVariable int libroId,
    		@RequestBody LibroRequestDto libroDto) {

        LibroResponseDto actualizado = libroService.update(libroId, libroDto);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);
    }
    
    //Eliminar libro
    @DeleteMapping("/libro/delete/{libroId}")
    ResponseEntity<?> deleteLibro(@PathVariable int libroId) {
        libroService.delete(libroId);
        return ResponseEntity.noContent().build();
    }
    
    //Mostrar usuarios
    @GetMapping("/usuario/all")
    ResponseEntity<?> findAllUsuarios() {
        return ResponseEntity.ok(usuarioService.findAll());
    }
    
    //Mostrar usuario por su Id
    @GetMapping("/usuario/byId/{usuarioId}")
    ResponseEntity<?> findOne(@PathVariable int usuarioId) {
        UsuarioResponseDto usuario = usuarioService.findById(usuarioId);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }
    
    // Crear usuario
    @PostMapping("/usuario/create")
    ResponseEntity<?> create(@RequestBody UsuarioRequestDto usuarioDto) {
        UsuarioResponseDto creado = usuarioService.create(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    //Actualizar usuario
    @PutMapping("/usuario/update/{idUsuario}")
    ResponseEntity<?> update(@PathVariable int idUsuario, 
    		@RequestBody UsuarioRequestDto usuarioDto) {

        UsuarioResponseDto actualizado = usuarioService.update(idUsuario, usuarioDto);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);
    }

    //Eliminar usuario
    @DeleteMapping("/usuario/delete/{usuarioId}")
    ResponseEntity<?> deleteUsuario(@PathVariable int usuarioId) {
        usuarioService.delete(usuarioId);
        return ResponseEntity.noContent().build();
    }
    
    // Mostrar prestamos
    @GetMapping("/prestamo/all")
    ResponseEntity<?> findAllPrestamos() {
        return ResponseEntity.ok(prestamoService.findAll());
    }
    
    //Crear préstamo
    @PostMapping("/prestamo/create")
    ResponseEntity<?> create(@RequestBody PrestamoRequestDto prestamoDto) {
        PrestamoResponseDto creado = prestamoService.create(prestamoDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    //Actualizar préstamo
    @PutMapping("/prestamo/update/{prestamoId}")
    ResponseEntity<?> update(@PathVariable int prestamoId,
    		@RequestBody PrestamoRequestDto prestamoDto) {

        PrestamoResponseDto actualizado = prestamoService.update(prestamoId, prestamoDto);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);
    }

    //Eliminar préstamo
    @DeleteMapping("/prestamo/delete/{prestamoId}")
    ResponseEntity<?> deletePrestamo(@PathVariable int prestamoId) {
        prestamoService.delete(prestamoId);
        return ResponseEntity.noContent().build();
    }
    
    //Mostrar reservas
    @GetMapping("/reserva/all")
    ResponseEntity<?> findAllReservas() {
        return ResponseEntity.ok(reservaService.findAll());
    }
    
    //Crear reserva
    @PostMapping("/reserva/create")
    ResponseEntity<?> create(@RequestBody ReservaRequestDto reservaDto) {
    	
        ReservaResponseDto creada = reservaService.create(reservaDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    //Actualizar reserva
    @PutMapping("/reserva/update/{reservaId}")
    ResponseEntity<?> update(@PathVariable int reservaId,
    		@RequestBody ReservaRequestDto reservaDto) {

        ReservaResponseDto actualizada = reservaService.update(reservaId, reservaDto);

        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizada);
    }

    //Eliminar reserva
    @DeleteMapping("/reserva/delete/{reservaId}")
    ResponseEntity<?> deleteReserva(@PathVariable int reservaId) {
        reservaService.delete(reservaId);
        return ResponseEntity.noContent().build();
    }
    
    //Mostrar roles
    @GetMapping("/rol/all")
	ResponseEntity<?> findAllRoles() {
		return ResponseEntity.ok(rolService.findAll());
	}
    
    //Crear rol
    @PostMapping("/rol/create/{rol}")
	ResponseEntity<?> create(@PathVariable String rol) {
		return ResponseEntity.status(HttpStatus.CREATED).body(rolService.create(rol));
	}
	
    //Actualizar rol
	@PutMapping("/rol/update/{idRol}")
	ResponseEntity<?> update(@PathVariable int idRol,
			@RequestBody RolRequestDto dto) {
		Rol actualizado = rolService.update(idRol, dto);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
		
		/*System.out.println("ID ROL PATH: " + idRol); 
		System.out.println("DTO RECIBIDO: " + dto); 
		System.out.println("DTO.NOMBRE: " + dto.getNombre());*/
		
        return ResponseEntity.ok(actualizado);
	}
	
	//Eliminar rol
	@DeleteMapping("/rol/delete/{rolId}")
	ResponseEntity<?> deleteRol(@PathVariable int rolId) {
		rolService.delete(rolId);
        return ResponseEntity.noContent().build();
	}
}
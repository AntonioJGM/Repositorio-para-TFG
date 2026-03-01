package com.tfg.modelo.restcontroller;

import java.util.HashMap;
import java.util.Map;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.tfg.modelo.dtos.UsuarioLoginDto;
import com.tfg.modelo.entities.Rol;
import com.tfg.modelo.entities.Usuario;
import com.tfg.modelo.repositories.RolRepository;
import com.tfg.modelo.repositories.UsuarioRepository;
import com.tfg.modelo.security.JwtService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "**")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired private 
    PasswordEncoder passwordEncoder;
    
    @Autowired private 
    RolRepository rolRepository;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioLoginDto loginRequest) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
		    new UsernamePasswordAuthenticationToken(
		        loginRequest.getUsername(), loginRequest.getPassword()
		    )
		);

        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getUsername())
                .orElseThrow();
        
        String token = jwtService.generateToken(
                usuario.getEmail(),
                usuario.getRol().getNombre()
        );
		String rol = usuario.getRol().toString();

		Map<String, String> response = new HashMap<>();
		response.put("token", token);
		response.put("rol", rol);
		response.put("email", loginRequest.getUsername());

		return ResponseEntity.ok(response);
    }
    
    @PostMapping("/register") 
    public ResponseEntity<?> register(@RequestBody Usuario usuario) { 
    	
    	//Comprobar si el email ya existe 
    	if (usuarioRepository.existsByEmail(usuario.getEmail())) { 
    		return ResponseEntity.badRequest().body(Map.of("message","El email ya está registrado")); 
    	} 
    	
    	//Encriptar contraseña 
    	usuario.setPassword(passwordEncoder.encode(usuario.getPassword())); 
    	
    	//Activar usuario 
    	usuario.setActivo(true); 
    	
    	//Asignar rol por defecto 
    	Rol rolUser = rolRepository.findByNombre("USUARIO")
    			.orElseThrow(() -> new RuntimeException("Rol USUARIO no encontrado")); 
    	usuario.setRol(rolUser); 
    	
    	//Guardar usuario 
    	usuarioRepository.save(usuario); 
    	return ResponseEntity.ok(Map.of("message", "Usuario registrado correctamente"));
 
    }
    
}
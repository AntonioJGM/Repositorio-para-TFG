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
import org.springframework.web.bind.annotation.*;

import com.tfg.modelo.dtos.UsuarioLoginDto;
import com.tfg.modelo.entities.Rol;
import com.tfg.modelo.entities.Usuario;
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
}
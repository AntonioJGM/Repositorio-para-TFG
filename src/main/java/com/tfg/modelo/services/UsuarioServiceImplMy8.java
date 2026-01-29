package com.tfg.modelo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tfg.modelo.dtos.UsuarioRequestDto;
import com.tfg.modelo.dtos.UsuarioResponseDto;
import com.tfg.modelo.entities.Rol;
import com.tfg.modelo.entities.Usuario;
import com.tfg.modelo.repositories.RolRepository;
import com.tfg.modelo.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImplMy8 implements UsuarioService, UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RolRepository rolRepository;
	
	private PasswordEncoder passwordEncoder;
	
	public UsuarioServiceImplMy8(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

	@Override
	public Usuario findById(Integer atributoId) {
		return usuarioRepository.findById(atributoId).orElse(null);
	}

	@Override
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario insertOne(Usuario entidad) {
		if (entidad== null || !usuarioRepository.existsById(entidad.getIdUsuario())) {
			return usuarioRepository.save(entidad);
		}
		return null;
	}

	@Override
	public Usuario updateOne(Usuario entidad) {
		if (usuarioRepository.existsById(entidad.getIdUsuario())) {
			return usuarioRepository.save(entidad);
		}
		return null;
	}

	@Override
	public void deleteOne(Integer atributoId) {
		usuarioRepository.deleteById(atributoId);
	}

	@Override
	public UsuarioResponseDto toResponseDTO(Usuario usuario) {
		return UsuarioResponseDto.builder()
				.usuarioId(usuario.getIdUsuario())
				.nombre(usuario.getNombre())
		        .apellidos(usuario.getApellidos())
		        .email(usuario.getEmail())
		        .rol(usuario.getRol().getNombre())
		        .build();
	}

	@Override
	public Usuario fromRequestDTO(UsuarioRequestDto dto) {
		
		Rol rol = rolRepository.findByNombre(dto.getRol())
				.orElseThrow(() -> new RuntimeException("Rol no válido"));
		
		return Usuario.builder()
		        .nombre(dto.getNombre())
		        .apellidos(dto.getApellidos())
		        .email(dto.getEmail())
		        .password(passwordEncoder.encode(dto.getPassword()))
		        .rol(rol)
		        .activo(true)
		        .build();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return usuarioRepository.findByEmail(username).orElse(null);
	}
	
}

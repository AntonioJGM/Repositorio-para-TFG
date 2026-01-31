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
import com.tfg.modelo.mappers.UsuarioMapper;
import com.tfg.modelo.repositories.RolRepository;
import com.tfg.modelo.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImplMy8 implements UsuarioService, UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RolRepository rolRepository;

	
	public UsuarioServiceImplMy8(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

	@Override
	public UsuarioResponseDto findById(int id) {
		return usuarioMapper.toResponseDto(usuarioRepository.findById(id).orElse(null));
	}

	@Override
	public List<UsuarioResponseDto> findAll() {
		return usuarioRepository.findAll().stream()
				.map(usuarioMapper::toResponseDto)
				.toList();
	}

	@Override
	public UsuarioResponseDto create(UsuarioRequestDto dto) {
		if (dto== null) {
			throw new IllegalArgumentException("El usuario no puede ser null");
		}
		Usuario nuevoUsuario = usuarioMapper.toEntity(dto);
		// Encriptar password 
		nuevoUsuario.setPassword(passwordEncoder.encode(dto.getPassword()));
		// Asignar rol 
		Rol rol = rolRepository.findByNombre(dto.getRol()) .orElseThrow(() -> new RuntimeException("Rol no encontrado")); nuevoUsuario.setRol(rol);
		// Activar Usuario 
		nuevoUsuario.setActivo(true);
		Usuario guardado = usuarioRepository.save(nuevoUsuario);
		return usuarioMapper.toResponseDto(guardado);
	}

	@Override
	public UsuarioResponseDto update(int id, UsuarioRequestDto dto) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Usuario no encontrado"));
		
		usuario.setNombre(dto.getNombre());
		usuario.setApellidos(dto.getApellidos());
		usuario.setEmail(dto.getEmail());
		usuario.setPassword(dto.getPassword());
		
		Usuario actualizado = usuarioRepository.save(usuario);
		
		return usuarioMapper.toResponseDto(actualizado);
	}

	@Override
	public void delete(int id) {
		
		if (!usuarioRepository.existsById(id))
			throw new RuntimeException("Usuario no encontrado"); 
		usuarioRepository.deleteById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return usuarioRepository.findByEmail(username).orElse(null);
	}
	
}

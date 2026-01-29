package com.tfg.modelo.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name="usuarios")
public class Usuario implements Serializable, UserDetails{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;
	private String nombre;
	private String apellidos;
	private String email;
	private String password;
	private boolean activo;
	@ManyToOne
	@JoinColumn(name="id_rol")
	private Rol rol;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		return List.of(new SimpleGrantedAuthority("ROLE_" + rol.getNombre()));
	}

	@Override
	public String getUsername() {
		return this.getEmail();
	}
	
}

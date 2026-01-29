package com.tfg.modelo.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tfg.modelo.entities.Rol;
import com.tfg.modelo.entities.Usuario;
import com.tfg.modelo.repositories.RolRepository;
import com.tfg.modelo.repositories.UsuarioRepository;

@Configuration
public class DataInitConfig {

    @Bean
    CommandLineRunner initAdmin(
            UsuarioRepository usuarioRepository,
            RolRepository rolRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            if (usuarioRepository.findByEmail("admin@admin.com").isEmpty()) {

                Rol admin = rolRepository.findByNombre("ADMIN")
                        .orElseThrow(() -> new RuntimeException("Rol ADMIN no existe"));

                Usuario usuario = Usuario.builder()
                        .nombre("Admin")
                        .apellidos("Admin")
                        .email("admin@admin.com")
                        .password(passwordEncoder.encode("admin"))
                        .rol(admin)
                        .activo(true)
                        .build();

                usuarioRepository.save(usuario);
            }
        };
    }
}
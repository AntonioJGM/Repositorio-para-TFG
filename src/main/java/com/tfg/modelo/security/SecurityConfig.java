package com.tfg.modelo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
		//Desactivamos CSRF porque tu API es REST y no usa formularios
		.csrf(csrf -> csrf.disable())
		//Indicamos que no queremos sesiones (API stateless)
		.sessionManagement(ses -> ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		//Activamos CORS para que Spring Security lo respete
		.cors(Customizer.withDefaults())
		//Configuramos qué rutas requieren autenticación
		.authorizeHttpRequests(auth -> auth
				//Permitir OPTIONS para que React pueda hacer preflight CORS
				.requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
				//El resto de rutas siguen requiriendo autenticación
				.anyRequest().authenticated())
		//Activamos autenticación básica (usuario/contraseña)
		.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
	
	//BCrypt : codificador de contraseñas 
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	        return authConfig.getAuthenticationManager();
	    }
	
	//Configuración global de CORS para permitir llamadas desde React (localhost:3000) 
	@Bean 
	public WebMvcConfigurer corsConfigurer() { 
		return new WebMvcConfigurer() { 
			@Override public void addCorsMappings(CorsRegistry registry) { 
				registry.addMapping("/**") // Todas las rutas 
				.allowedOrigins("http://localhost:4200") // Tu frontend React 
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos 
				.allowedHeaders("*") // Headers permitidos 
				.allowCredentials(true); // Permitir enviar credenciales (si usas auth básica) 
				} 
			}; 
			
	}
			
	

}

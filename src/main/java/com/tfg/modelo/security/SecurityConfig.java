package com.tfg.modelo.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtFilter) throws Exception {
		
		http
		//Desactivamos CSRF porque tu API es REST y no usa formularios
		.csrf(csrf -> csrf.disable())
		//Indicamos que no queremos sesiones (API stateless)
		.sessionManagement(ses -> ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		//Activamos CORS para que Spring Security lo respete
		.cors(Customizer.withDefaults())
		//Configuramos qué rutas requieren autenticación
		.authorizeHttpRequests(auth -> auth
				// Público
				.requestMatchers("/auth/**").permitAll()
				.requestMatchers("/swagger-ui/**","/v3/api-docs/**","/swagger-resources/**").permitAll()
				.requestMatchers("/libro/**").permitAll()
				.requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
				// Solo ADMIN
			    .requestMatchers("/admin/**").hasRole("ADMIN")
			    // Usuarios autenticados (usuario + bibliotecario + admin)
				.requestMatchers("/prestamo/**").authenticated()
				.requestMatchers("/reserva/**").authenticated()
				//El resto de rutas siguen requiriendo autenticación
				.anyRequest().authenticated())
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
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
	WebMvcConfigurer corsConfigurer() { 
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
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
	    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	    configuration.setAllowedHeaders(Arrays.asList("*"));
	    configuration.setAllowCredentials(true);
	    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));
	    configuration.setAllowedHeaders(Arrays.asList("*"));
	    
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}
			
	

}

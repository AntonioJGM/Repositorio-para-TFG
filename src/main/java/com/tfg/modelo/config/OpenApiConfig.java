package com.tfg.modelo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

	/*Sirve para activar y configurar la seguridad Bearer JWT dentro de Swagger / OpenAPI, de forma que Swagger UI pueda:
		mostrar el botón Authorize, aceptar tu token JWT, enviar ese token automáticamente en cada petición, 
		y permitirte probar endpoints protegidos sin errores 401.
		Sin esa clase, Swagger no sabe que tu API usa autenticación Bearer, y por eso no muestra el cuadro para 
		meter el token o no lo envía correctamente.*/
	
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }
}

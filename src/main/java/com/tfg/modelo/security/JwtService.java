package com.tfg.modelo.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtService {

    private final String SECRET = "clave_super_secreta_para_tfg_2026_muy_segura";

    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(String email, String role, int idUsuario) {
        
    	String emailUtf8 = new String(email.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
    	
    	return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .claim("idUsuario", idUsuario)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .signWith(getKey())
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    
    public String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            throw new RuntimeException("Token no encontrado o mal formado");
        }

        return header.substring(7); // elimina "Bearer "
    }
    
    public String extractUsername(String token) { 
    	return extractEmail(token); // en tu JWT, el subject es el email 
    }

}

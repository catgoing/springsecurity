package com.example.config;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

@Component
public class JWTUtil {
	
    private SecretKey secretKey; //JWT 토큰 객체 키를 저장할 시크릿 키  
    
    public JWTUtil(@Value("${jwt.secretkey}") String secret) {  
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());  
    }  
  
    public String getUsername(String token) {  
  
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("username",String.class);  
    }  
  
    public String getRole(String token) {  
  
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);  
    }  
  
    public boolean isExpired(String token) {  
  
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());  
    }  
  
    public String generateAccessToken(String username, String role, Long expiredMs) {  
  
        return Jwts.builder()  
                .claim("username", username)  
                .claim("role", role)  
                .issuedAt(new Date(System.currentTimeMillis()))  
                .expiration(new Date(System.currentTimeMillis() + expiredMs))  
                .signWith(secretKey)  
                .compact();  
    }
    
    public String generateRefreshToken(String username, String role, Long expiredMs) {  
    	
    	return Jwts.builder()  
    			.claim("username", username)  
    			.claim("role", role)  
    			.issuedAt(new Date(System.currentTimeMillis()))  
    			.expiration(new Date(System.currentTimeMillis() + expiredMs))  
    			.signWith(secretKey)  
    			.compact();  
    }

}

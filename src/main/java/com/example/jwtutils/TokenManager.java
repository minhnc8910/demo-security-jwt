package com.example.jwtutils;

import com.example.model.Role;
import com.example.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TokenManager implements Serializable {
    private static final long serialVersionUID = 7008375124389347049L;
    public static final long TOKEN_VALIDITY = 60000;
    @Value("${secret}")
    private String jwtSecret;
    public String generateJwtToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuer(user.getRole().getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 60)) // phut
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }
    public Boolean validateJwtToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        Boolean isTokenExpired = claims.getExpiration().before(new Date());
        return (username.equals(userDetails.getUsername()) && !isTokenExpired);
    }
    public String getUsernameFromToken(String token) {
        final Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}

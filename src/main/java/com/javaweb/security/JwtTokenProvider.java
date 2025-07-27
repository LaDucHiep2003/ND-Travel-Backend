package com.javaweb.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {
    private final Key JWT_SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final long JWT_EXPIRATION = 86400000; // 1 ngày

    public String generateToken(UserDetails userDetails) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + JWT_EXPIRATION);

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .map(role -> role.replace("ROLE_", "")) // nếu prefix là ROLE_
                .toList();

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles", roles)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}

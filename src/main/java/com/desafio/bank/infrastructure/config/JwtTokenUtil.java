package com.desafio.bank.infrastructure.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
    private final JwtConfiguration jwtConfiguration;

    public String generateToken(String username) {
        return Jwts.builder()
                .claim("sub", username)
                .claim("iat", Date.from(Instant.now()))
                .claim("exp", Date.from(Instant.now().plusMillis(jwtConfiguration.getExpirationTime())))
                .signWith(getSecretKey())
                .compact();
    }

    public boolean validateToken(String token, String username) {
        if (token == null) return false;

        Claims claims = Jwts.parser()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getPayload();
        boolean isSubValid = claims.getSubject().equals(username);
        boolean isExpValid = claims.getExpiration().after(new Date());

        return isSubValid && isExpValid;
    }

    public String getUsernameFromToken(String token) {
        if (token == null) return null;

        Claims claims = Jwts.parser()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getPayload();

        return claims.getSubject();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtConfiguration.getSecret().getBytes(StandardCharsets.UTF_8));
    }
}

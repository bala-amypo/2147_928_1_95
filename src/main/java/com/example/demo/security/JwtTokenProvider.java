package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Date;

public class JwtTokenProvider {

    private final SecretKey secretKey;
    private final long validityInMs;

    public JwtTokenProvider(String password, long validityInMs) {
        this.secretKey = generateKeyFromPassword(password);
        this.validityInMs = validityInMs;
    }

    private SecretKey generateKeyFromPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] keyBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Keys.hmacShaKeyFor(keyBytes);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate JWT key", e);
        }
    }

    public String generateToken(Authentication authentication,
                                Long userId,
                                String email,
                                String role) {

        Claims claims = Jwts.claims().setSubject(String.valueOf(userId));
        claims.put("email", email);
        claims.put("role", role);

        Date now = new Date();
        Date expiry = new Date(now.getTime() + validityInMs);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ REQUIRED: subject fallback without signature verification
    public Long getUserIdFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return Long.parseLong(claims.getSubject());

        } catch (Exception e) {
            try {
                // 🔥 fallback: ignore signature
                Claims claims = Jwts.parserBuilder()
                        .build()
                        .parseClaimsJwt(token)
                        .getBody();

                return Long.parseLong(claims.getSubject());

            } catch (Exception ex) {
                return null;
            }
        }
    }

    public String getEmailFromToken(String token) {
        try {
            return (String) Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get("email");
        } catch (Exception e) {
            return null;
        }
    }

    public String getRoleFromToken(String token) {
        try {
            return (String) Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get("role");
        } catch (Exception e) {
            return null;
        }
    }
}

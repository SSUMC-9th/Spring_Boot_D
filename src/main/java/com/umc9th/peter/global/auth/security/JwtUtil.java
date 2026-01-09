package com.umc9th.peter.global.auth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final Duration accessTokenExpiration;
    private final Duration refreshTokenExpiration;

    public JwtUtil(
            @Value("${jwt.token.secret-key}") String secret,
            @Value("${jwt.token.expiration.access}") Long accessTokenExpiration,
            @Value("${jwt.token.expiration.refresh}") Long refreshTokenExpiration
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpiration = Duration.ofMillis(accessTokenExpiration);
        this.refreshTokenExpiration = Duration.ofMillis(refreshTokenExpiration);
    }

    public String createAccessToken(CustomUserDetails user) {
        return createToken(user, accessTokenExpiration, "access_token");
    }

    public String createRefreshToken(CustomUserDetails user) {
        return createToken(user, refreshTokenExpiration, "refresh_token");
    }

    public String getEmail(String token) {
        try {
            return getClaims(token).getPayload().getSubject();
        } catch (JwtException e) {
            return null;
        }
    }

    public boolean isValidAccessToken(String token) {
        return isValidToken(token, "access_token");
    }

    public boolean isValidRefreshToken(String token) {
        return isValidToken(token, "refresh_token");
    }

    private boolean isValidToken(String token, String tokenType) {
        try {
            Jws<Claims> claims = getClaims(token);
            String type = claims.getPayload().get("token_type", String.class);
            return tokenType.equals(type);
        } catch (JwtException e) {
            return false;
        }
    }

    private String createToken(CustomUserDetails user, Duration expiration, String tokenType) {
        Instant now = Instant.now();

        String authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .subject(user.getUsername())
                .claim("role", authorities)
                .claim("email", user.getUsername())
                .claim("token_type", tokenType)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(expiration)))
                .signWith(secretKey)
                .compact();
    }

    private Jws<Claims> getClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .clockSkewSeconds(60)
                .build()
                .parseSignedClaims(token);
    }
}

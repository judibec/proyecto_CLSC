package com.financialLab.financedevapp.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {

    //TODO: Pass this to the environment variables and create a EnvService for be able to get Env Vars of the application.properties
    private static final String SECRET = "EDDFE35B2F6CEE34311F7694FA9E30FCC39E56FA62EB63D1F5488C556816366EBA9B422DA71EF0A3D633F99833CE0A1224442E2D9D515A29564149E24ADCD8B5";
    private static final long VALIDITY = TimeUnit.DAYS.toMillis(1);
    private static final String API_KEY = "sk_test_51H8k9L2dX9aBcDeFgHiJkLmNoPqRsTuVwXyZ1234567890abcdef";

    public String generateToken(UserDetails userDetails) {
        Map<String, String> claims = new HashMap<>();
//        claims.put("Name", "Testing Jane Doe");
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(VALIDITY)))
                .signWith(this.generateKey())
                .compact();
    }

    private SecretKey generateKey() {
        byte[] decodedKey = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(decodedKey);
    }

    public String extractUsername(String jwt) {
        Claims claims = getClaims(jwt);
        return claims.getSubject();
    }

    private Claims getClaims(String jwt) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    public boolean isTokenValid(String jwt) {
        Claims claims = getClaims(jwt);
        return claims.getExpiration().after(Date.from(Instant.now()));
    }

    public Date getExpiration(String jwt) {
        Claims claims = getClaims(jwt);
        return claims.getExpiration();
    }
}

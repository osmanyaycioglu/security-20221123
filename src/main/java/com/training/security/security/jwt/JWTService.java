package com.training.security.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {
    private Key key;

    public JWTService() {
        key = Keys.hmacShaKeyFor("jhfdhkjshflksdnvksjdvksdvksjvnksjvnksjdvknjdhksfhsdf".getBytes());
    }

    public String generateToken(UserDetails userDetailsParam,
                                String ip) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("ip",
                   ip);
        Date nowDate = new Date();
        return Jwts.builder()
                   .setIssuedAt(nowDate)
                   .setExpiration(new Date(nowDate.getTime() + (30 * 1000 * 60)))
                   .addClaims(claims)
                   .setSubject(userDetailsParam.getUsername())
                   .signWith(key)
                   .compact();
    }

    public Jws<Claims> check(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (Exception eParam) {
            eParam.printStackTrace();
        }
        return null;
    }

}

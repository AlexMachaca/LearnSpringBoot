package com.iis.app.Jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.iis.app.entity.TUser;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;
    //private static final String secret="miKeyDavid";
    //private static final Long expiration=3600000L;


    private Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(String idUser, String nameUser) {
        System.out.println(expiration);
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", idUser);
        claims.put("nameUser", nameUser);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(nameUser)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);

            // Verificar la expiracion del token
            Date now = new Date();
            if (claimJws.getBody().getExpiration().before(now)) {
                return false;
            }
            return true;
        } catch (SignatureException ex) {
            // La firma del token es invalida
            return false;
        } catch (Exception e) {
            // Otra excepcion
            return false;
        }
    }
}

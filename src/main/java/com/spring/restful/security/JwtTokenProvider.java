package com.spring.restful.security;

import io.jsonwebtoken.*;
import com.spring.restful.entity.User;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtTokenProvider {

    private final String jwtSecret = "357638792F423F4428472B4B6250655368566D5MATAMU97133743677397A2443264629";

    private final int jwtExpirationInMs = 1000 * 60 * 60;

    public Date extractExpiration(String token) {
        return extratClaim(token, Claims::getExpiration);
    }

    public String extractUsername(String token) {
        return extratClaim(token, Claims::getSubject);
    }

    public <T> T extratClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public String generateToken(User user) {
//        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
//        return createToken(userPrincipal.getUsername());
        return createToken(user.getUsername());
    }

    public String generateRefreshToken(HashMap<String, Object> extraClaims, User user) {
        return createRefreshToken(extraClaims, user.getUsername());
    }

    private String createRefreshToken(HashMap<String, Object> extraClaims, String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, getSignKey())
                .compact();
    }

    private String createToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, getSignKey())
                .compact();
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSignKey())
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

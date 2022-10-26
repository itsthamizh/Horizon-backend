package com.example.bike_registration.security;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Service
public class JwtProvider {

    public String generateToken(UserDetails user){

//        String token =  Jwts.builder()
//                .setSubject(user.getUsername())
//                .claim("Authorities_key", authorities)
//                .signWith(key)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis()+30*1000))
//                .compact();

        String token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .sign(HMAC512(JwtProperties.SECRET.getBytes()));

        return token;
    }


    public boolean validate_token(String jwt) {
        Jwts.parser().setSigningKey((Key) HMAC512(JwtProperties.SECRET.getBytes())).parseClaimsJws(jwt);
        return true;
    }

    public String getEmailFromJWT(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey((Key) HMAC512(JwtProperties.SECRET.getBytes()))
                .parseClaimsJws(jwt)
                .getBody();
        return claims.getSubject();
    }
}

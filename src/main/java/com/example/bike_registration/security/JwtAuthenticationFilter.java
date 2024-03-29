package com.example.bike_registration.security;

import com.auth0.jwt.JWT;
import com.example.bike_registration.Model.LoginViewModel;
import com.example.bike_registration.Model.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JwtAuthenticationFilter {
    private AuthenticationManager authenticationManager;

//    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }

    /* Trigger when we issue POST request to /login
        {"username":"xxxx@gmail.com", "password":"Xxx@123"}
     */
    public String attemptAuthentication(User users) {

        // Grab credentials and map them to login viewmodel
//        LoginViewModel credentials = null;
//        try {
//            credentials = new ObjectMapper().readValue(request.getInputStream(), LoginViewModel.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // Create login token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                users.getUsername(),
                users.getPassword(),
                new ArrayList<>());

        // Grab principal
//        UserPrincipal principal = (UserPrincipal) authenticationToken.getPrincipal();

        // Create JWT Token
        String token = JWT.create()
                .withSubject(users.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .sign(HMAC512(JwtProperties.SECRET.getBytes()));

        // Add token in response
//        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX +  token);

        return token;
    }

//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        // Grab principal
//        UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();
//
//        // Create JWT Token
//        String token = JWT.create()
//                .withSubject(principal.getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
//                .sign(HMAC512(JwtProperties.SECRET.getBytes()));
//
//        // Add token in response
//        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX +  token);
//    }
}

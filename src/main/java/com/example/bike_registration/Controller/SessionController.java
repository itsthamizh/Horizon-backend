package com.example.bike_registration.Controller;

import com.example.bike_registration.CommonParams.LoginRequest;
import com.example.bike_registration.Exception.UsernameNotFoundException;
import com.example.bike_registration.ResponseHandler.ResponseHandler;
import com.example.bike_registration.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth/v1")
public class SessionController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = POST)
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest){
        System.out.println("This is input userName "+ loginRequest.getUsername());
        System.out.println("This is input password "+ loginRequest.getPassword());

        try{
            String token = userService.loginUser(loginRequest);
            Map<String, String> token_map = new HashMap<>();
            token_map.put("token", token);
            return ResponseHandler.generateResponse("Login Successfully", HttpStatus.OK, token_map);
        }
        catch (UsernameNotFoundException e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.valueOf(201), null);
        }
    }
}

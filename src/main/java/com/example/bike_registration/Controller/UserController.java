package com.example.bike_registration.Controller;

import com.example.bike_registration.Model.Users;
import com.example.bike_registration.Service.UserServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.bike_registration.Exception.UserAlreadyExistException;
import com.example.bike_registration.Model.Users;
import com.example.bike_registration.Repository.UserRepository;
import com.example.bike_registration.ResponseHandler.ResponseHandler;
import com.example.bike_registration.Service.UserServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
@Slf4j
public class UserController {

    @Autowired
    private UserServiceInterface userServiceInterface;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    List<Users> getAllUsers(){
        return userServiceInterface.findAll();
    }

    //Create Users
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<Object> addUser(@RequestBody Users users){
        try {
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString();
            users.setId(id);
            users.setStatus("active");
            Users user_result = userServiceInterface.addUser(users);
            return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.OK, user_result);
        }
        catch (UserAlreadyExistException e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.valueOf(201), null);
        }
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    Users updateUser(@PathVariable("id") String id, @RequestBody Users users){
        userServiceInterface.updateUser(id, users);
        return users;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
        Optional getUserById(@PathVariable("id") String id){
        Optional users = userServiceInterface.findById(id);
        return users;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    String deleteUser(@PathVariable("id") UUID id){
        userServiceInterface.deleteUserById(id);
        return "Successfully Deleted..!";
    }
}
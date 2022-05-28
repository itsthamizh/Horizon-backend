package com.example.bike_registration.Controller;

import com.example.bike_registration.Model.Users;
import com.example.bike_registration.Service.UserServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/users")
    List<Users> getAllUsers(){
        return userServiceInterface.findAll();
    }

    //Create all adminUsers
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    Users addUser(@RequestBody Users users){
        log.info("This is for users : ",users);
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        users.setId(id);
        userServiceInterface.addUser(users);
        return users;
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
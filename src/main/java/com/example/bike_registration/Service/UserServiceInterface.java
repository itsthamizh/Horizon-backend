package com.example.bike_registration.Service;

import com.example.bike_registration.Model.Users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserServiceInterface {
    List<Users> findAll();

    Users addUser(Users users);

    Users updateUser(String id, Users users);

    Optional<Users> findById(String id);

    void deleteUserById(UUID id);
}

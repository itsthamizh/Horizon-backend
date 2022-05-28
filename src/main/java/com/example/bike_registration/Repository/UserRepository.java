package com.example.bike_registration.Repository;

import com.example.bike_registration.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, String> {
    Users findByUsername(String username);

    Optional<Users> findById(UUID id);
    void deleteById(UUID id);
}

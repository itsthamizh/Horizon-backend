package com.example.bike_registration.Service;

import com.example.bike_registration.Model.Users;
import com.example.bike_registration.Repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

//        User_Seed.delete_all_user();
        // Delete all
//        this.userRepository.deleteAll();
//
//        User_Seed.user_create();
//         Crete users
//        Users dan = new Users(1,"dan", "dan@gmail.com", passwordEncoder.encode("User@12345"), "USER", "112233", "");
//        Users admin = new Users(1, "admin", "admin@gmail.ocm", passwordEncoder.encode("User@12345"), "ADMIN", "223344", "ACCESS_TEST1,ACCESS_TEST2");
//        Users manager = new Users(1, "manager","manager@gmail.com", passwordEncoder.encode("User@12345"), "MANAGER", "334455","ACCESS_TEST1");
//
//        List<Users> users = Arrays.asList(dan,admin,manager);
//
//        // Save to db
//
//        this.userRepository.saveAll(users);
    }
}

package com.example.bike_registration.Service;

import com.example.bike_registration.CommonParams.LoginRequest;
import com.example.bike_registration.Exception.UserAlreadyExistException;
import com.example.bike_registration.Model.Users;
import com.example.bike_registration.Repository.UserRepository;
import com.example.bike_registration.security.JwtAuthenticationFilter;
import com.example.bike_registration.security.JwtProvider;
import com.example.bike_registration.security.UserPrincipalDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserPrincipalDetailsService userPrincipalDetailsService;
    @Autowired
    private LoginService loginService;

    @Autowired
    JwtProvider jwtProvider;

    private JwtAuthenticationFilter jwtAuthenticationFilter;

    private final PasswordEncoder passwordEncoder;

    public List<Users> findAll() {
        return userRepository.findAll();
    }

    public Users addUser(Users users) throws UserAlreadyExistException {
        if(checkIfUserExist(users.getUsername())){
            throw new UserAlreadyExistException("User already exists for this email");
        }

        users.setRole("USER");
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        userRepository.save(users);
        return users;
    }

    public Optional<Users> findById(String id) {
        return userRepository.findById(id);
    }

    public Users updateUser(String id, Users users){
        if (userRepository.findById(id).isPresent()) {
            Users get_user = userRepository.findById(id).get();
            get_user.setId(id);
            get_user.setName(users.getName());
            get_user.setUsername(users.getUsername());
            get_user.setPhone_no(users.getPhone_no());
            get_user.setRole(users.getRole());
            get_user.setActive(users.getActive());
            get_user.setPermissions(users.getPermissions());

            Users updateUser = userRepository.save(get_user);
            return updateUser;
        }
        else {
            return users;
        }
    }

    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }


    public String loginUser(LoginRequest loginRequest) throws UsernameNotFoundException{

        UserDetails user
                = loginService.loadUserByUsername(loginRequest.getUsername());

        if (user.getUsername() != null && user.getPassword() != null){
            String token =  jwtProvider.generateToken(user);
            return token;
        }
        else {
            throw new UsernameNotFoundException("No user found");
        }
    }


    public boolean checkIfUserExist(String email){
       return userRepository.findByUsername(email) !=null ? true : false;
    }

    public String encodePassword(String password){
        return passwordEncoder.encode(password);
    }
}

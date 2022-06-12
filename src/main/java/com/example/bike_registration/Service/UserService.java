package com.example.bike_registration.Service;

import com.example.bike_registration.Exception.UserAlreadyExistException;
import com.example.bike_registration.Model.Users;
import com.example.bike_registration.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
public class UserService implements UserServiceInterface, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(email);
        if (user == null){
            log.error("User not found in the database ");
            throw new UsernameNotFoundException("User Not found in the databases");
        }
        else{
            log.info("User found in the database: {}", email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Users addUser(Users users) throws UserAlreadyExistException {
        if(checkIfUserExist(users.getUsername())){
            throw new UserAlreadyExistException("User already exists for this email");
        }

        users.setRole("USER");
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        userRepository.save(users);
        return users;
    }

    @Override
    public Optional<Users> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
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

    @Override
    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean checkIfUserExist(String email){
       return userRepository.findByUsername(email) !=null ? true : false;
    }
}

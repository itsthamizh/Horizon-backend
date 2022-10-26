package com.example.bike_registration.Service;

import com.example.bike_registration.Model.Users;
import com.example.bike_registration.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email){
        Users user = userRepository.findByUsername(email);

        if (user == null) {
            return new org.springframework.security.core.userdetails.User(
                    null, null, null);
        } else {
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    getAuthorities(user.getRole()));
        }
    }


    private Collection<? extends GrantedAuthority> getAuthorities(String role){
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_"+role));
    }
}

package com.elijahhelmandollar.expensiph.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.elijahhelmandollar.expensiph.entity.User;
import com.elijahhelmandollar.expensiph.dao.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {

        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user == null) {

            throw new UsernameNotFoundException("Username " + username + " was not found.");

        }

        return new org.springframework.security.core.userdetails.User(

                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))

        );

    }

}

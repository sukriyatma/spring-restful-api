package com.spring.restful.service.impl;

import com.spring.restful.service.CustomUserDetailsService;
import com.spring.restful.entity.User;
import com.spring.restful.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Component
public class CustomUserDetailsServiceImpl implements UserDetailsService, CustomUserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findFirstByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with this username %s", username)));

        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), Collections.emptyList());
    }
}

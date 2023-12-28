package com.spring.restful.service.impl;

import com.spring.restful.model.*;
import com.spring.restful.security.JwtTokenProvider;
import com.spring.restful.service.ValidationService;
import com.spring.restful.entity.User;
import com.spring.restful.repository.UserRepository;
import com.spring.restful.security.BCrypt;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.UUID;

@Service
public class AuthServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Transactional
    public JwtAuthResponse login(LoginUserRequest request) {
        validationService.validate(request);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = userRepository.findById(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong"));

        if (BCrypt.checkpw(request.getPassword(), user.getPassword())){
            var token = jwtTokenProvider.generateToken(user);
            var refreshToken = jwtTokenProvider.generateRefreshToken(new HashMap<>(), user);
            user.setToken(token);
            user.setRefreshToken(refreshToken);
            userRepository.save(user);

            return JwtAuthResponse.builder()
                    .token(token)
                    .refreshToken(refreshToken)
                    .build();
        } else  {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong");
        }

    }

    @Transactional
    public UserResponse register(RegisterUserRequest request) {
        validationService.validate(request);

        if (userRepository.existsById(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
        }

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        newUser.setName(request.getName());

        userRepository.save(newUser);

        return UserResponse.builder()
                .username(newUser.getUsername())
                .name(newUser.getName())
                .build();
    }

    private Long next30Days() {
        return System.currentTimeMillis() + (1000 * 60 * 24 * 30);
    }

}

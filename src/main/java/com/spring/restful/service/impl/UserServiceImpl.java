package com.spring.restful.service.impl;

import com.spring.restful.service.UserService;
import com.spring.restful.service.ValidationService;
import com.spring.restful.entity.User;
import com.spring.restful.model.*;
import com.spring.restful.repository.UserRepository;
import com.spring.restful.security.UserPrincipal;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    @Override
    public UserResponse getCurrent(UserPrincipal currentUser) {

        return UserResponse.builder()
                .username(currentUser.getUsername())
                .name(currentUser.getName())
                .build();
    }

    @Transactional
    @Override
    public UserResponse update(UpdateUserRequest request, UserPrincipal currentUser) {
        validationService.validate(request);

        User user = userRepository.getUserByUsername(currentUser);

        if (Objects.nonNull(request.getName())) {
            user.setName(request.getName());
        }

        if (Objects.nonNull(request.getPassword())) {
            user.setPassword(request.getPassword());
        }

        userRepository.save(user);
        return UserResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .build();
    }
}

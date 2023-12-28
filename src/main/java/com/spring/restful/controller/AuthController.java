package com.spring.restful.controller;

import com.spring.restful.service.impl.AuthServiceImpl;
import com.spring.restful.service.ValidationService;
import com.spring.restful.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    ValidationService validationService;

    @Autowired
    AuthServiceImpl authService;

    @PostMapping(
            path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<JwtAuthResponse>> login(@RequestBody LoginUserRequest request) {
        JwtAuthResponse jwtAuthResponse = authService.login(request);

        return new ResponseEntity<>(
                WebResponse.<JwtAuthResponse>builder()
                        .data(jwtAuthResponse)
                        .status(HttpStatus.OK.name())
                        .code(HttpStatus.OK.value())
                        .build(),
                HttpStatus.OK
        );

    }

    @PostMapping(
            value = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<UserResponse>> register(@RequestBody RegisterUserRequest request) {
        UserResponse response = authService.register(request);

        return new ResponseEntity<>(
                WebResponse.<UserResponse>builder()
                        .data(response)
                        .code(HttpStatus.CREATED.value())
                        .status(HttpStatus.CREATED.name())
                        .build(),
                HttpStatus.CREATED
        );

    }

}

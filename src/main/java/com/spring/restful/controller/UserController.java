package com.spring.restful.controller;

import com.spring.restful.service.UserService;
import com.spring.restful.model.*;
import com.spring.restful.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(
            value = "/current",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> get(UserPrincipal currentUser) {
        UserResponse response = userService.getCurrent(currentUser);

        return WebResponse.<UserResponse>builder()
                .data(response)
                .build();
    }


    @PatchMapping(
            value = "/current",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<UserResponse>> update(@RequestBody UpdateUserRequest request, UserPrincipal currentUser) {
        UserResponse response = userService.update(request,currentUser);

        return new ResponseEntity<>(
                WebResponse.<UserResponse>builder()
                        .data(response)
                        .code(HttpStatus.CREATED.value())
                        .status("UPDATED")
                        .build(),
                HttpStatus.CREATED
        );
    }
}

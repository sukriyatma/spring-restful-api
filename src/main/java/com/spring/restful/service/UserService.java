package com.spring.restful.service;

import com.spring.restful.model.*;
import com.spring.restful.security.UserPrincipal;

public interface UserService {

//    UserResponse register(RegisterUserRequest request);

    UserResponse update(UpdateUserRequest request, UserPrincipal currentUser);

    UserResponse getCurrent(UserPrincipal currentUser);

}

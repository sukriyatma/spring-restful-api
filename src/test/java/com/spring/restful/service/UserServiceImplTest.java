package com.spring.restful.service;

import com.spring.restful.service.impl.UserServiceImpl;
import com.spring.restful.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTest {

    @Mock
    @Autowired
    UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    void setUp() {
        userService = Mockito.spy(userService);
        userRepository.deleteAll();
    }

//    @Test
//    public void testRegister() {
//        String username = "sukriyatma";
//        String password = "matamu";
//        String name = "Muhammad Sukriyatma";
//
//        RegisterUserRequest request = new RegisterUserRequest();
//        request.setUsername(username);
//        request.setPassword(password);
//        request.setName(name);
//
//        UserResponse responseExpected = new UserResponse();
//        responseExpected.setUsername(username);
//        responseExpected.setName(name);
//
//        Assertions.assertEquals(responseExpected, userService.register(request));
//    }
}
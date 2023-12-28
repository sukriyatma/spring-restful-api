package com.spring.restful.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.restful.entity.User;
import com.spring.restful.model.LoginUserRequest;
import com.spring.restful.model.RegisterUserRequest;
import com.spring.restful.model.WebResponse;
import com.spring.restful.repository.UserRepository;
import com.spring.restful.security.BCrypt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void testRegisterSucces() throws Exception{
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("sukriyatma");
        request.setPassword("matamu");
        request.setName("Muhammad Sukriyatma");

        mockMvc.perform(
                        post("/api/v1/auth/register")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(request))
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.username").value(request.getUsername()))
                .andExpect(jsonPath("$.data.name").value(request.getName()));

    }


    @Test
    public void testLoginSuccess() throws Exception {
        User user = new User();
        user.setUsername("sukriyatma");
        user.setPassword(BCrypt.hashpw("matamu", BCrypt.gensalt()));
        user.setName("Muhammad Sukriyatma");
        userRepository.save(user);

        LoginUserRequest request = new LoginUserRequest();
        request.setUsername("sukriyatma");
        request.setPassword("matamu");

        mockMvc.perform(
                        post("/api/v1/auth/login")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//                .andExpect(jsonPath("$.data.token").value(userRepository.findById(user.getUsername()).get().getToken()))
    }
}
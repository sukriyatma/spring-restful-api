package com.spring.restful.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "users"
)
public class User {

    @Id
    private String username;

    private String password;

    private String name;

    private String token;

    @Column(name = "token_refresh")
    private String refreshToken;
}

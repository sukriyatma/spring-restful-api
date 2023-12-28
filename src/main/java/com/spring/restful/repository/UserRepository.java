package com.spring.restful.repository;

import com.spring.restful.entity.User;
import com.spring.restful.security.UserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findFirstByToken(String token);

    Optional<User> findFirstByUsername(String username);

    default User getUserByUsername(UserPrincipal currentUser) {
        return findFirstByUsername(currentUser.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cant find the username"));
    }
}

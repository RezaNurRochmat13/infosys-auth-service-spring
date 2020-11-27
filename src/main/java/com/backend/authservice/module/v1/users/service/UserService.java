package com.backend.authservice.module.v1.users.service;

import com.backend.authservice.module.v1.users.dao.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    User registerUser(User userPayload);
    ResponseEntity<String> loginUser(User userPayload);
}

package com.backend.authservice.module.v1.users.presenter;

import com.backend.authservice.module.v1.users.dao.User;
import com.backend.authservice.module.v1.users.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/")
public class UserPresenter {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("register")
    public Map<String, Object> registerNewUser(@RequestBody User userPayload) {
        Map<String, Object> map = new HashMap<>();
        User userCreated = userService.registerUser(userPayload);
        map.put("message", "User Registered Successfully");
        map.put("created_user", userCreated);
        return map;
    }

    @PostMapping("login")
    public ResponseEntity<String> loginUser(@RequestBody User userPayload) {
        return userService.loginUser(userPayload);
    }

}

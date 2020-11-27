package com.backend.authservice.module.v1.users.service;

import com.backend.authservice.module.v1.users.dao.User;
import com.backend.authservice.module.v1.users.repository.UserRepository;
import com.backend.authservice.module.v1.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User userPayload) {
        User user = new User();
        user.setUsername(userPayload.getUsername());
        user.setPassword(passwordEncoder.encoder().encode(userPayload.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<String> loginUser(User userPayload) {
        User user = userRepository.findByUsername(userPayload.getUsername());
        Boolean authorizeAccess = passwordEncoder.encoder()
                .matches(userPayload.getPassword(), user.getPassword());
        if (authorizeAccess == true) {
            return new ResponseEntity<String>("Sukses login", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Gagal login. Cek username dan password", HttpStatus.UNAUTHORIZED);
        }
    }
}

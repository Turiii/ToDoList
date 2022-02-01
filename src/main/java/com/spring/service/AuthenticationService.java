package com.spring.service;

import com.spring.dao.UserEntity;
import com.spring.dto.LoginRequest;
import com.spring.dto.RegisterRequest;
import com.spring.mapper.UserMapper;
import com.spring.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Transactional
    public void register(RegisterRequest registerRequest) {
        UserEntity isInDatabase = userRepository.findByUsername(registerRequest.getUsername());
        if (isInDatabase == null) {
            UserEntity userEntity = userMapper.fromDto(registerRequest);
            userRepository.save(userEntity);
        } else {
            log.error("Username " + isInDatabase.getUsername() + " already exists.");
        }
    }

    public void login(LoginRequest loginRequest) {

    }
}



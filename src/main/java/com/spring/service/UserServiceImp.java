package com.spring.service;

import com.spring.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp extends UserService {


    public UserServiceImp(UserRepository userRepository) {
        super(userRepository);
    }
}


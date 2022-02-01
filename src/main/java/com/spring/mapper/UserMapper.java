package com.spring.mapper;

import com.spring.dao.UserEntity;
import com.spring.dto.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserEntity fromDto(RegisterRequest registerRequest){

        UserEntity userEntity=new UserEntity();
        userEntity.setUsername(registerRequest.getUsername());
        userEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userEntity.setIsActive(true);
        return userEntity;
    }
}

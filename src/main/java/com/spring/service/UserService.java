package com.spring.service;

import com.spring.dao.UserEntity;
import com.spring.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    public void register(UserEntity userAccount) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userAccount.getUsername());
        userEntity.setPassword(userAccount.getPassword());
        userEntity.setRole(userAccount.getRole());
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByUsername(userName);
    }

    public UserDetails findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public List<UserEntity> showAllUsers() {
        return userRepository.findAll();
    }

    public void removeUserById(Long id) {
        UserEntity userToDelete = userRepository.findUserById(id);
        userRepository.delete(userToDelete);
    }

}

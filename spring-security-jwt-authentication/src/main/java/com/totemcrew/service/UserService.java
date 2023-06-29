package com.totemcrew.service;

import com.totemcrew.models.User;
import com.totemcrew.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveAll(List<User> users) {
        userRepository.saveAll(users);
    }
}
package com.learnspringboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnspringboot.dto.UserRequest;
import com.learnspringboot.enums.Role;
import com.learnspringboot.exception.UserNotFoundException;
import com.learnspringboot.models.User;
import com.learnspringboot.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(UserRequest userRequest) {
        User user = User.build(
                0,
                userRequest.getFirstname(),
                userRequest.getLastname(),
                userRequest.getEmail(),
                userRequest.getPassword(),
                Role.USER);

        return userRepository.save(user);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    // public User getUser(int userId) throws UserNotFoundException {
    // User user = userRepository.findByUserId(userId);
    // if (user != null) {
    // return user;
    // } else {
    // throw new UserNotFoundException("user not found with id : " + userId);
    // }
    // }
}

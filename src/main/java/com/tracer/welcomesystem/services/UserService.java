package com.tracer.welcomesystem.services;// UserService.java
import com.tracer.welcomesystem.models.User;
import com.tracer.welcomesystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getAllUsers() {



        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    //login
    public Boolean login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }


}

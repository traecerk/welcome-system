package com.tracer.welcomesystem.controller;

import com.tracer.welcomesystem.services.UserService;
import com.tracer.welcomesystem.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
public class UserController {
    final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/user")
    public User getUserInfo(@RequestParam(value = "id") Long id){
        return userService.getUserById(id);
    }

    @GetMapping("/saveUser")
    public User saveUser(@RequestParam(value = "id") Long id, @RequestParam(value = "name") String name, @RequestParam(value = "email") String email){
        User user = new User(id, name, email);
        return userService.saveUser(user);
    }



    @GetMapping("/user/list")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

}

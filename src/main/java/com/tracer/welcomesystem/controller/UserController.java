package com.tracer.welcomesystem.controller;

import com.tracer.welcomesystem.services.UserService;
import com.tracer.welcomesystem.models.User;
import com.tracer.welcomesystem.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;


@CrossOrigin
@RestController
@RequestMapping("/user")
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

    @GetMapping("/save")
    public User saveUser(@RequestParam(value = "stuid") String stuid, @RequestParam(value = "name") String name, @RequestParam(value = "email") String email){
        User user = new User(stuid, name, email);
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public RespBean login(@RequestBody HashMap<String, String> user){
        String email = user.get("email");
        String password = user.get("password");
        System.out.println(email + " " + password);

        Boolean login = userService.login(email, password);
        if (login){
            return RespBean.ok("Login successful", null);
        } else {
            System.out.println("Invalid email or password");
            return RespBean.error("Invalid email or password");
        }
    }

    @GetMapping("/list")
    public RespBean getAllUsers(){

        HashMap<Object,Object> map= new HashMap<>();
        List<User> users = userService.getAllUsers();
        map.put("total", users.size());
        map.put("items", users);

        return RespBean.ok("User list", map);
    }

}

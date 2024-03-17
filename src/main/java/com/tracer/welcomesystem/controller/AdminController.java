package com.tracer.welcomesystem.controller;

import com.tracer.welcomesystem.models.Admin;
import com.tracer.welcomesystem.services.AdminService;
import com.tracer.welcomesystem.services.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RequestMapping("/admin")
@RestController
public class AdminController {
    final AdminService adminService;
    final AuthService authService;

    public AdminController(AdminService adminService, AuthService authService) {
        this.adminService = adminService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody Map<String, String> admin) {
        String email = admin.get("email");
        String password = admin.get("password");
        adminService.saveAdmin(new Admin(email, password));
        System.out.println(email + " registered successfully");
        return "Admin registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> admin) {
        String email = admin.get("email");
        String password = admin.get("password");
        Admin admin1 = adminService.getAdminByEmail(email);
        if (Objects.equals(admin1.getPassword(), password)){
            String token = authService.generateToken();
            authService.storeToken(email, token);
            System.out.println(email + " logged in successfully");
            //设置cookie

            return token;
        } else {
            System.out.println("Invalid email or password");
            return "Invalid email or password";
        }
    }

}

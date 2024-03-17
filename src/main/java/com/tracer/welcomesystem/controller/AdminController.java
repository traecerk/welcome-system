package com.tracer.welcomesystem.controller;

import com.tracer.welcomesystem.models.Admin;
import com.tracer.welcomesystem.services.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/admin")
@RestController
public class AdminController {
    final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register")
    public String register(@RequestBody Map<String, String> admin) {
        String username = admin.get("username");
        String password = admin.get("password");
        adminService.saveAdmin(new Admin(username, password));
        return "Admin registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> admin) {
        String name = admin.get("username");
        String password = admin.get("password");
        Admin admin1 = adminService.getAdminByName(name);
        if (admin1 != null) {
            return "Login successful";
        } else {
            return "Invalid credentials";
        }
    }

}

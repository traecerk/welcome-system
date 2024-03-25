package com.tracer.welcomesystem.controller;

import com.tracer.welcomesystem.models.Admin;
import com.tracer.welcomesystem.services.AdminService;
import com.tracer.welcomesystem.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import com.tracer.welcomesystem.utils.JsonUtils;
import java.util.Map;
import java.util.Objects;

@RequestMapping("/admin")
@RestController
@CrossOrigin
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
    public String login(@RequestBody Map<String, String> admin ,
                        HttpServletResponse response){
        System.out.println("Login");
        String email = admin.get("username");
        String password = admin.get("password");
        System.out.println(email + " " + password);
        Admin admin1 = adminService.getAdminByEmail(email);
        if (Objects.equals(admin1.getPassword(), password)){
            String token = authService.generateToken();
            authService.storeToken(email, token);
            Cookie cookie = new Cookie("token", token);
            response.addCookie(cookie);
            return JsonUtils.toJson("data:{token:" + token + "}");
        } else {
            System.out.println("Invalid email or password");
            return "Invalid email or password";
        }
    }

}

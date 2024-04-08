package com.tracer.welcomesystem.controller;

import com.tracer.welcomesystem.models.Admin;
import com.tracer.welcomesystem.services.AdminService;
import com.tracer.welcomesystem.services.AuthService;
import com.tracer.welcomesystem.utils.RespBean;
import io.lettuce.core.dynamic.annotation.Value;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import com.tracer.welcomesystem.utils.JsonUtils;

import java.util.HashMap;
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
    public RespBean login(@RequestBody Map<String, String> admin ,
                          HttpServletResponse response){
        System.out.println("Login");
        String email = admin.get("username");
        String password = admin.get("password");
        System.out.println(email + " " + password);


        Admin admin1 = adminService.login(email, password);
        if (admin1 != null){
            String token = authService.generateToken();
            authService.storeToken(email, token);
            Cookie cookie = new Cookie("token", token);
            response.addCookie(cookie);
            HashMap<String, String> map = new HashMap<>();
            map.put("token", token);
            return RespBean.ok( "Login successful",map);
        } else {
            System.out.println("Invalid email or password");
            return RespBean.error("Invalid email or password");
        }
    }

    @GetMapping("/info")
    public RespBean getInfo(@RequestParam(value = "token") String token,
            HttpServletRequest request){
        System.out.println("Get info");
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "admin");
        map.put("avatar", "123");
        map.put("roles", new String[]{"admin"});
        return RespBean.ok("Admin info", map );
    }

    @PostMapping("/logout")
    public RespBean logout(@RequestParam(value = "token") String token){
        System.out.println("Logout");
        authService.storeToken(token, "");
        return RespBean.ok("Logout successful", "");
    }

}

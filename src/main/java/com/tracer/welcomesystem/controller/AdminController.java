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
            authService.storeToken(admin1, token);
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

    @PostMapping("/modify")
    public RespBean modifyAdmin(@RequestBody HashMap<String, String> admin){
        Admin admin1 = adminService.getAdminByEmail(admin.get("email"));
        admin1.setEmail(admin.get("email"));
        admin1.setPassword(admin.get("Newpassword"));
        admin1.setName(admin.get("name"));
        adminService.saveAdmin(admin1);
        HashMap<Object,Object> map= new HashMap<>();
        map.put("admin", admin1);
        return RespBean.ok("Admin modified", map);
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

    @GetMapping("/aInfo")
    public RespBean getAdminInfo(HttpServletRequest request){
        String token = request.getHeader("X-Token");

        if (authService.validateToken(token)){
            Object admin1 = authService.getObject(token);
            HashMap<String, Object> map = new HashMap<>();
            map.put("admin", admin1);
            return RespBean.ok("Admin info", admin1);
        } else {
            return RespBean.error("Invalid token");
        }


    }

    @PostMapping("/logout")
    public RespBean logout(){
        return RespBean.ok("Logout successful", "");
    }

}

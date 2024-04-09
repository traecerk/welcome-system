package com.tracer.welcomesystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class AuthService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String generateToken() {
        return UUID.randomUUID().toString();
    }

    public void storeToken(Object object, String token) {
        redisTemplate.opsForValue().set(token, object.toString());
        redisTemplate.expire(object.toString(), 30, TimeUnit.MINUTES); // 设置token过期时间为30分钟
        System.out.println(object.toString() + " " + token + " stored in redis");
    }

    public boolean validateToken(String token) {
        System.out.println("Token: " + token);
        String email = redisTemplate.opsForValue().get(token);
        System.out.println(email);
        return email != null;
    }

    public Object getObject(String token) {
        return redisTemplate.opsForValue().get(token);
    }
    public String getEmail(String token) {
        return redisTemplate.opsForValue().get(token);
    }
}
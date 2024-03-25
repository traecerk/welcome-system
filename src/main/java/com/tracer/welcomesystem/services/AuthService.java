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

    public void storeToken(String email, String token) {
        redisTemplate.opsForValue().set(email, token);
        redisTemplate.expire(email, 30, TimeUnit.MINUTES); // 设置token过期时间为30分钟
    }

    public boolean validateToken(String username, String token) {
        String storedToken = redisTemplate.opsForValue().get(username);
        return token.equals(storedToken);
    }
}
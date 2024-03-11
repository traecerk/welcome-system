package com.tracer.welcomesystem.repositories;


@Repository
public interface UserRepository {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    
}

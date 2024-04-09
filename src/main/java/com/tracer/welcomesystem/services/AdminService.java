package com.tracer.welcomesystem.services;

import com.tracer.welcomesystem.models.Admin;
import com.tracer.welcomesystem.models.User;
import com.tracer.welcomesystem.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService (AdminRepository adminrepository) {
        this.adminRepository = adminrepository;

    }

    public Admin getAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    public Admin saveAdmin(Admin admin) {
        Admin save = null;
        Optional<Admin> adminOptional = adminRepository.findById(admin.getAdmin_id());
        if (adminOptional.isPresent()) {
            Admin admin1 = adminOptional.get();
            admin1.setAdmin_id(admin.getAdmin_id());
            admin1.setEmail(admin.getEmail());
            admin1.setPassword(admin.getPassword());
            save = adminRepository.save(admin1);
        } else {
            save = adminRepository.save(admin);
        }
        return save;


    }

    public Admin login(String email, String password) {
        Admin admin = adminRepository.findByEmail(email);
        if (admin != null) {
            return admin.getPassword().equals(password) ? admin : null;
        }
        return null;
    }




}

package com.tracer.welcomesystem.services;

import com.tracer.welcomesystem.models.Admin;
import com.tracer.welcomesystem.models.User;
import com.tracer.welcomesystem.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService (AdminRepository adminrepository) {
        this.adminRepository = adminrepository;

    }

    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }



   public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
    }
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    public Admin getAdminByName(String username) {
        return adminRepository.findByName(username);
    }



}

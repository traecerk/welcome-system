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

    public Admin login(String email, String password) {
        Admin admin = adminRepository.findByEmail(email);
        if (admin != null) {
            return admin.getPassword().equals(password) ? admin : null;
        }
        return null;
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

    public Admin getAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    public Admin getAdminByEmailAndPassword(String email, String password) {
        return adminRepository.findByEmailAndPassword(email, password);
    }


}

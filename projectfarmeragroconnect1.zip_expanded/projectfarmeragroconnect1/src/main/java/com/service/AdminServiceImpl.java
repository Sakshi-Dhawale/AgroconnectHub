package com.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import com.exception.AdminNotFoundException;
import com.exception.AdminNotFoundException;
import com.model.Admin;
import com.dao.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    

    @Override
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin getOneAdmin(String username) {
        try {
			return adminRepository.findById(username)
			        .orElseThrow(() -> new AdminNotFoundException("Admin not found with username: " + username));
		} catch (AdminNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

    @Override
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    @Override
    public Map<String, Object> deleteAdmin(String username) throws AdminNotFoundException {
        Map<String, Object> response = new HashMap<>();
        try {
            Admin admin = adminRepository.findByUsername(username);
            if (admin == null) {
                throw new AdminNotFoundException("Admin not found for username: " + username);
            }
            adminRepository.delete(admin);
            response.put("status", "Admin deleted successfully");
        } catch (AdminNotFoundException ex) {
            response.put("status", "Admin not deleted: " + ex.getMessage());
        }
        return response;
    }							


    @Override
    public Admin updateAdmin(Admin admin) throws AdminNotFoundException {
        Admin existingAdmin = adminRepository.findByUsername(admin.getUsername());
        
        if (existingAdmin == null) {
            throw new AdminNotFoundException("Admin not found with username: " + admin.getUsername());
        }
        
        // Update relevant fields of existingAdmin with admin's data
        existingAdmin.setPassword(admin.getPassword());
        // Add more fields to update as necessary
        
        return adminRepository.save(existingAdmin); // Save and return updated admin
    }


    @Override
    public List<Admin> getAllAdminsWithFarmers() {
        return adminRepository.findAllWithFarmers(); // Custom method in AdminRepository to fetch admins with eager fetching of farmers
    }
    
    @Override
    public boolean authenticate(String userName, String password, String role) throws AdminNotFoundException {
        Admin admin = adminRepository.findByUsername(userName);

        if (admin == null) {
            throw new AdminNotFoundException("Admin with username " + userName + " not found.");
        }

        return admin.getPassword().equals(password);
    }
}

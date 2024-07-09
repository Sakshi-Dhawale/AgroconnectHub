package com.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.AdminNotFoundException;
import com.model.Admin;
import com.model.Farmer;
import com.model.Admin;
import com.service.AdminService;

@RestController
@RequestMapping("/agroch")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
	
	private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/adminLogin")
    public ResponseEntity<Boolean> loginAdmin(@RequestBody Admin request) {
        boolean authenticated = false;
        try {
            authenticated = adminService.authenticate(request.getUsername(), request.getPassword(), request.getRole());
        } catch (AdminNotFoundException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(authenticated);
    }


    @PostMapping("/addAdmin")
    public ResponseEntity<Admin> saveAdmin(@RequestBody Admin admin) {
        Admin savedAdmin = adminService.saveAdmin(admin);
        return ResponseEntity.status(HttpStatus.CREATED).header("Status:Add", "Admin Succcessfully Added").body(savedAdmin);
    }

    @GetMapping("/getAllAdmins")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdminsWithFarmers(); // Ensure farmers are fetched
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }



    @DeleteMapping("/deleteAdmin/{username}")
    public ResponseEntity<Map<String, Object>> deleteAdmin(@PathVariable("username") String username) throws AdminNotFoundException {
        Map<String, Object> response = adminService.deleteAdmin(username);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateAdmin")
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) {
        Admin updatedAdmin = null;
		try {
			updatedAdmin = adminService.updateAdmin(admin);
		} catch (AdminNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ResponseEntity.status(HttpStatus.ACCEPTED).header("Update", "Admin updated").body(updatedAdmin);
    }

}

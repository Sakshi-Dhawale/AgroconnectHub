package com.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;


import com.exception.AdminNotFoundException;
import com.model.Admin;


public interface AdminService 
{
	
	public Admin saveAdmin(Admin admin);
	
	public Admin getOneAdmin(String username);
	
	public List<Admin> getAllAdmin();
	
    // Optional<Admin> findAllByUsername(String name);
	 public Map<String,Object> deleteAdmin(String username) throws AdminNotFoundException;
	 
	 public Admin updateAdmin(Admin admin) throws AdminNotFoundException;

	 List<Admin> getAllAdminsWithFarmers();
	
	 public boolean authenticate(String username, String password, String role) throws AdminNotFoundException;
	

}

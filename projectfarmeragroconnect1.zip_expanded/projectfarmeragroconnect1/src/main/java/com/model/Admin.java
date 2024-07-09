package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Admin {
    @Id
    private String username;
    private String password;
    private String role = "Admin";
    
    @OneToMany(mappedBy = "admin")
    private List<Farmer> farmers = new ArrayList<>();


    public Admin() {
    }

	public Admin(String username, String password, String role, List<Farmer> farmers) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}   
}

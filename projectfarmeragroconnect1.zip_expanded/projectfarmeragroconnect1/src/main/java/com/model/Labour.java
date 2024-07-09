package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Labour {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String username;
	    private String password;
	    private String fullName;
	    private String gender;
	    private String address;
	    private long phone;
	    private String role="Labour";
	    private String labourType;
	    private String email;
	   
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "farmer_id")
	    private Farmer farmer;

		public Labour() {
			super();
		}

		public Labour(Long id, String username, String password, String fullName, String gender, String address,
				long phone, String role, String labourType, String email, Farmer farmer) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
			this.fullName = fullName;
			this.gender = gender;
			this.address = address;
			this.phone = phone;
			this.role = role;
			this.labourType = labourType;
			this.email = email;
			this.farmer = farmer;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public long getPhone() {
			return phone;
		}

		public void setPhone(long phone) {
			this.phone = phone;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public String getLabourType() {
			return labourType;
		}

		public void setLabourType(String labourType) {
			this.labourType = labourType;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Farmer getFarmer() {
			return farmer;
		}

		public void setFarmer(Farmer farmer) {
			this.farmer = farmer;
		}
}

package com.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {

	Admin findByUsername(String username);
	@Query("SELECT DISTINCT a FROM Admin a LEFT JOIN FETCH a.farmers")
    List<Admin> findAllWithFarmers();
}

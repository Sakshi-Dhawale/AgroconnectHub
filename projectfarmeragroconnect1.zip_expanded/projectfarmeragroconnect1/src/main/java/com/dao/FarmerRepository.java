package com.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Admin;
import com.model.Farmer;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {


	Farmer findByUsername(String username);
}

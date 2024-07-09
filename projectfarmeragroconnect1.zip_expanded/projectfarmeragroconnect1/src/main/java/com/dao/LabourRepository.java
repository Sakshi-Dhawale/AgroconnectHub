package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Labour;

@Repository
public interface LabourRepository extends JpaRepository<Labour, Long> {

	Labour findByUsername(String username);
}

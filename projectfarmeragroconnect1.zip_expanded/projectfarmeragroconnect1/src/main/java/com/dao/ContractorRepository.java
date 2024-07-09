package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Contractor;

@Repository
public interface ContractorRepository extends JpaRepository<Contractor, Long>{
	Contractor findByUsername(String username);
}

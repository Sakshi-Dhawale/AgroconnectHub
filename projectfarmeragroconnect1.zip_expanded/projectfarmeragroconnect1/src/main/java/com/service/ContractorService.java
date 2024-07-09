package com.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.exception.ContractorNotFoundException;
import com.model.Contractor;
import com.model.Farmer;

public interface ContractorService {
	
	public Contractor addContractor(Contractor contractor);
	
	public  List<Contractor> findAll();
	
	Optional<Contractor> findById(Long id); 
	
	Optional<Contractor> findAllByUsername(String name);

	public Map<String,Object> deleteContractor(long id)throws ContractorNotFoundException;
	
	public Contractor updateContractor(Contractor contractor);

	public boolean authenticate(String username, String password, String role) throws ContractorNotFoundException;

}

package com.service;

import com.exception.RetailerNotFoundException;
import com.model.Retailer;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RetailerService {
   
    public Retailer save(Retailer retailer);
    
    public  List<Retailer> findAll();

	public Map<String,Object> deleteRetailer(long id)throws RetailerNotFoundException;
	
	public Retailer updateRetailer(Retailer retailer);

    Optional<Retailer> findById(Long id); 
    
    Optional<Retailer> findByUsername(String username); 
    
    public boolean authenticate(String username, String password, String role) throws RetailerNotFoundException;

   
}

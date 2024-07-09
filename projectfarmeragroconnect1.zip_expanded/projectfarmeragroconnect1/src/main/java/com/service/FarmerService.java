package com.service;

import com.exception.FarmerNotFoundException;
import com.model.Farmer;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface FarmerService {

    Farmer saveFarmer(Farmer farmer);

    List<Farmer> findAll();

    Map<String, Object> deleteFarmer(long id);

    Farmer updateFarmer(Farmer farmer);
    
    Optional<Farmer> findById(Long id); 
    
    Optional<Farmer> findByUsername(String username); 
    
    public boolean authenticate(String username, String password, String role) throws FarmerNotFoundException;

}

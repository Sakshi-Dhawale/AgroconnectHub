package com.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.exception.LabourNotFoundException;
import com.model.Labour;

public interface LabourService {
	
	public Labour addLabour(Labour labour);

	public  List<Labour> findAll();

	public Map<String,Object> deleteLabour(long id)throws LabourNotFoundException;
	
	public Labour updateLabour(Labour labour);
	
    Optional<Labour> findById(Long id); 
    
    Optional<Labour> findByUsername(String username); 
    
    public boolean authenticate(String username, String password, String role) throws LabourNotFoundException;


}

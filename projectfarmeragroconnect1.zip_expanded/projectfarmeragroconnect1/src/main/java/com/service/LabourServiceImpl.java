package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.LabourRepository;
import com.exception.FarmerNotFoundException;
import com.exception.LabourNotFoundException;
import com.model.Farmer;
import com.model.Labour;

@Service
public class LabourServiceImpl implements LabourService{
	
	@Autowired
	private LabourRepository labourRepo;

	@Override
	public Labour addLabour(Labour labour) {
	
		return labourRepo.save(labour);
	}

	@Override
	public List<Labour> findAll() {
	
		return labourRepo.findAll();
	}

	@Override
	public Map<String, Object> deleteLabour(long id) throws LabourNotFoundException
	{
		Map<String,Object> response=new HashMap<String,Object>();
		try {
			Labour labour=labourRepo.findById(id).orElseThrow(()->new LabourNotFoundException("Labour not found"));
			labourRepo.delete(labour);
			response.put("deleted", Boolean.TRUE);
		}catch(LabourNotFoundException e)
		{
			response.put("not deleted", e.getMessage());
		}
		return response;
	}
	@Override
	public Labour updateLabour(Labour labour) {
		Labour l=null;
		try {
			l=labourRepo.findById(labour.getId()).orElseThrow(()->new LabourNotFoundException("Labour not exist"));
			l.setFullName(labour.getFullName());
			l.setAddress(labour.getAddress());
            l.setPhone(labour.getPhone());
            l.setEmail(labour.getEmail());          
            l.setGender(labour.getGender());
            l.setUsername(labour.getUsername());
            l.setPassword(labour.getPassword());
			
		}catch(LabourNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		return labourRepo.save(l);
	}

	@Override
	public Optional<Labour> findById(Long id) {
		
		return labourRepo.findById(id);
	}

	@Override
	public Optional<Labour> findByUsername(String username) {
		
		return Optional.ofNullable(labourRepo.findByUsername(username));
	}

	@Override
	public boolean authenticate(String username, String password, String role) throws LabourNotFoundException {
		Labour labour = labourRepo.findByUsername(username);

        if (labour == null) {
            throw new LabourNotFoundException("Admin with username " + username + " not found.");
        }

        return labour.getPassword().equals(password);
	}	
}






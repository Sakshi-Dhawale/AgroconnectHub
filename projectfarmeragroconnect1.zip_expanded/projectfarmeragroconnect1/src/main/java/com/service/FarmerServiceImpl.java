package com.service;

import com.dao.FarmerRepository;
import com.exception.AdminNotFoundException;
import com.exception.FarmerNotFoundException;
import com.model.Admin;
import com.model.Farmer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FarmerServiceImpl implements FarmerService {

    @Autowired
    private FarmerRepository farmerRepository;

    @Override
    public Farmer saveFarmer(Farmer farmer) {
        return farmerRepository.save(farmer);
    }

    @Override
    public List<Farmer> findAll() {
        return farmerRepository.findAll();
    }

    @Override
    public Map<String, Object> deleteFarmer(long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Farmer farmer = farmerRepository.findById(id).orElseThrow(() -> new FarmerNotFoundException("Farmer not found"));
            farmerRepository.delete(farmer);
            response.put("deleted", Boolean.TRUE);
        } catch (FarmerNotFoundException e) {
            response.put("not deleted", e.getMessage());
        }
        return response;
    }

    @Override
    public Farmer updateFarmer(Farmer farmer) {
        Farmer f = null;
        try {
            f = farmerRepository.findById(farmer.getId()).orElseThrow(() -> new FarmerNotFoundException("Farmer not found"));
            f.setFullName(farmer.getFullName());
            f.setAddress(farmer.getAddress());
            f.setPhone(farmer.getPhone());
            f.setEmail(farmer.getEmail());          
            f.setGender(farmer.getGender());
            f.setUsername(farmer.getUsername());
            f.setPassword(farmer.getPassword());
        } catch (FarmerNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return farmerRepository.save(f);
    }

    @Override
    public Optional<Farmer> findById(Long id) {
        return farmerRepository.findById(id);
    }

	@Override
	public boolean authenticate(String username, String password, String role) throws FarmerNotFoundException {
		Farmer farmer = farmerRepository.findByUsername(username);

        if (farmer == null) {
            throw new FarmerNotFoundException("Admin with username " + username + " not found.");
        }

        return farmer.getPassword().equals(password);
	}

	@Override
	public Optional<Farmer> findByUsername(String username) {
		return Optional.ofNullable(farmerRepository.findByUsername(username));
	}
}

package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ContractorRepository;
import com.exception.AdminNotFoundException;
import com.exception.ContractorNotFoundException;
import com.exception.FarmerNotFoundException;
import com.model.Admin;
import com.model.Contractor;
import com.model.Farmer;

@Service
public class ContractorServiceImpl implements ContractorService{
	
	@Autowired
	private ContractorRepository contractorRepo;

	@Override
	public Contractor addContractor(Contractor contractor) {
		
		return contractorRepo.save(contractor);
	}

	@Override
	public List<Contractor> findAll() {
	
		return contractorRepo.findAll();
	}

	@Override
	public Map<String, Object> deleteContractor(long id) throws ContractorNotFoundException {
		Map<String,Object> response=new HashMap<String,Object>();
		try {
			Contractor contractor=contractorRepo.findById(id).orElseThrow(()->new ContractorNotFoundException("Contractor not found"));
			contractorRepo.delete(contractor);
			response.put("deleted", Boolean.TRUE);
		}catch(ContractorNotFoundException e)
		{
			response.put("not deleted", e.getMessage());
		}
		return response;
	}

	@Override
    public Contractor updateContractor(Contractor contrtactor) {
        Contractor f = null;
        try {
            f = contractorRepo.findById(contrtactor.getId()).orElseThrow(() -> new FarmerNotFoundException("Contractor not found"));
            f.setFullName(contrtactor.getFullName());
            f.setAddress(contrtactor.getAddress());
            f.setPhone(contrtactor.getPhone());
            f.setEmail(contrtactor.getEmail());          
            f.setGender(contrtactor.getGender());
            f.setUsername(contrtactor.getUsername());
            f.setPassword(contrtactor.getPassword());
        } catch (FarmerNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return contractorRepo.save(f);
    }

	@Override
    public boolean authenticate(String username, String password, String role) throws ContractorNotFoundException {
        Contractor contractor = contractorRepo.findByUsername(username);

        if (contractor == null) {
            throw new ContractorNotFoundException("Contractor with username " + username + " not found.");
        }
        // Check if passwords match and role matches
        if (contractor.getPassword().equals(password) && contractor.getRole().equals(role)) {
            return true;
        }
        return false;
    }


	@Override
	public Optional<Contractor> findAllByUsername(String username) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(contractorRepo.findByUsername(username));
	}

	@Override
	public Optional<Contractor> findById(Long id) {
		// TODO Auto-generated method stub
		return contractorRepo.findById(id);
	}}

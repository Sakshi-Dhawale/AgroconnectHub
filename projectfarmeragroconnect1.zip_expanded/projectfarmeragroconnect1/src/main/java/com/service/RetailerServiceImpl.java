package com.service;



import com.dao.RetailerRepository;
import com.exception.FarmerNotFoundException;
import com.exception.RetailerNotFoundException;
import com.model.Farmer;
import com.model.Retailer;

import com.service.RetailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RetailerServiceImpl implements RetailerService {
    @Autowired
    private RetailerRepository retailerRepository;

    @Override
    public Retailer save(Retailer retailer) {
        return retailerRepository.save(retailer);
    }

    @Override
    public List<Retailer> findAll() {
        return retailerRepository.findAll();
    }

	@Override
	public Map<String, Object> deleteRetailer(long id) throws RetailerNotFoundException 
	{
		Map<String,Object> response=new HashMap<String,Object>();
		try {
			Retailer retailer=retailerRepository.findById(id).orElseThrow(()->new RetailerNotFoundException("Retailer not found"));
			retailerRepository.delete(retailer);
			response.put("deleted", Boolean.TRUE);
		}catch(RetailerNotFoundException e)
		{
			response.put("not deleted", e.getMessage());
		}
		return response;
	}

	@Override
	public Retailer updateRetailer(Retailer retailer) {
		Retailer r=null;
		try {
			r=retailerRepository.findById(retailer.getId()).orElseThrow(()->new RetailerNotFoundException("Retailer not exist"));
			r.setFullName(retailer.getFullName());
			r.setAddress(retailer.getAddress());
            r.setPhone(retailer.getPhone());
            r.setEmail(retailer.getEmail());          
            r.setGender(retailer.getGender());
            r.setUsername(retailer.getUsername());
            r.setPassword(retailer.getPassword());
			
		}catch(RetailerNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		return retailerRepository.save(r);
	}

	@Override
	public Optional<Retailer> findById(Long id) {
		
		return retailerRepository.findById(id);
	}

	@Override
	public Optional<Retailer> findByUsername(String username) {
		
		return Optional.ofNullable(retailerRepository.findByUsername(username));
	}

	@Override
	public boolean authenticate(String username, String password, String role) throws RetailerNotFoundException {
		
		Retailer retailer = retailerRepository.findByUsername(username);

        if (retailer == null) {
            throw new RetailerNotFoundException("Admin with username " + username + " not found.");
        }

        return retailer.getPassword().equals(password);
	}	
}


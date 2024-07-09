package com.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.DetailsRepository;
import com.model.Details;

import java.util.List;
@Service
public class DetailsService {

    @Autowired
    private DetailsRepository detailsRepository;

    public List<Details> getAllDetails() {
        return detailsRepository.findAll();
    }

    public List<Details> getDetailsByContractor(String contractor) {
        return detailsRepository.findByContractor(contractor);
    }

    public Details getDetailsById(Long id) {
        return detailsRepository.findById(id).orElse(null);
    }

    public Details saveDetails(Details details) {
        return detailsRepository.save(details);
    }

    public void deleteDetails(Long id) {
        detailsRepository.deleteById(id);
    }
}
package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ResourceRepository;
import com.model.Resources;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceRepository resourceRepo;

	@Override
	public Resources saveresources(Resources resources) {
		
		return resourceRepo.save(resources);
	}
}

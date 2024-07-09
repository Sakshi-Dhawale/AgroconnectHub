package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Resources;
import com.model.Retailer;
import com.service.ResourceService;

@RestController
@RequestMapping("/agroch")
public class ResourceController {
	
	@Autowired
	private ResourceService resourceServiece;
	
	@PostMapping("/addResource")
	public ResponseEntity<Resources> saveresources(@RequestBody Resources resources) {
		Resources reso = resourceServiece.saveresources(resources);
        return ResponseEntity.status(HttpStatus.CREATED).header("Add","Resource added").body(reso);
    }
	

}

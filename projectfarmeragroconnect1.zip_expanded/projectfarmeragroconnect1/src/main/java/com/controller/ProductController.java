package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Product;
import com.model.Resources;
import com.service.ProductService;

@RestController
@RequestMapping("/agroch")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/addProduct")
	public ResponseEntity<Product> saveproduct(@RequestBody Product product) {
		Product prod = productService.saveproduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).header("Add","Product added").body(prod);
    }

}

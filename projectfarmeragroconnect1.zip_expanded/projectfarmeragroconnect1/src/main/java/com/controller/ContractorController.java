package com.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dao.ContractorRepository;
import com.dao.FarmerRepository;
import com.exception.ContractorNotFoundException;
import com.model.Admin;
import com.model.Contractor;
import com.model.Farmer;
import com.service.ContractorService;

//ContractorController.java

@RestController
@RequestMapping("/agroch")
@CrossOrigin(origins = "http://localhost:4200")
public class ContractorController {

 private final ContractorService contractorService;

 @Autowired
 public ContractorController(ContractorService contractorService) {
     this.contractorService = contractorService;
 }
 
 @Autowired
 private ContractorRepository contractorRepository;

 @PostMapping("/contractorLogin")
 public ResponseEntity<Boolean> loginContractor(@RequestBody Map<String, String> request) {
     String username = request.get("username");
     String password = request.get("password");
     String role = request.get("role");

     System.out.println("Received login request for user: " + username + " with role: " + role);

     boolean authenticated = false;
     try {
         authenticated = contractorService.authenticate(username, password, role);
     } catch (ContractorNotFoundException e) {
         e.printStackTrace();
     }
     return ResponseEntity.ok(authenticated);
 }

 @PostMapping("/addContractor")
 public ResponseEntity<Contractor> addContractor(@RequestBody Contractor contractor) {
     Contractor savedContractor = contractorService.addContractor(contractor);
     return ResponseEntity.status(HttpStatus.CREATED).header("Add", "Resource added").body(savedContractor);
 }

 @GetMapping("/getAllContractors")
 public List<Contractor> getAllContractors() {
     return contractorService.findAll();
 }
 
 @GetMapping("/getOneContractor/{id}")
 public ResponseEntity<Contractor> getContractorById(@PathVariable Long id) {
     Optional<Contractor> contractor = contractorService.findById(id);
     return contractor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
 }
 
 @GetMapping("/getContractorByUsername/{username}")
 public ResponseEntity<?> getContractorByUsername(@PathVariable String username) {
     try {
         Contractor contractor = contractorRepository.findByUsername(username);
         if (contractor != null) {
             return ResponseEntity.ok(contractor);
         } else {
             return ResponseEntity.notFound().build(); // Or handle not found case appropriately
         }
     } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving contractor by username: " + e.getMessage());
     }
 }
 

 @DeleteMapping("/deleteContractor/{id}")
 public ResponseEntity<Map<String, Object>> deleteContractor(@PathVariable("id") long id) throws ContractorNotFoundException {
     Map<String, Object> response = contractorService.deleteContractor(id);
     return ResponseEntity.ok(response);
 }

 
 @PutMapping("/updateContractor/{id}")
 public ResponseEntity<Contractor> updateContractor(@RequestBody Contractor contractor) {
     Contractor updatedContractor = contractorService.updateContractor(contractor);
     return ResponseEntity.status(HttpStatus.OK).header("Update", "Contractor updated").body(updatedContractor);
 }
}

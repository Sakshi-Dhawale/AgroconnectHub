package com.controller;

import com.exception.FarmerNotFoundException;
import com.model.Farmer;
import com.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/agroch")
@CrossOrigin(origins = "http://localhost:4200")
public class FarmerController {
    @Autowired
    private FarmerService farmerService;
    
    @PostMapping("/farmerLogin")
    public ResponseEntity<Boolean> loginFarmer(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        String role = request.get("role");

        System.out.println("Received login request for user: " + username + " with role: " + role);

        boolean authenticated = false;
        try {
            authenticated = farmerService.authenticate(username, password, role);
        } catch (FarmerNotFoundException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(authenticated);
    }

    @PostMapping("/addFarmer")
    public ResponseEntity<Farmer> saveFarmer(@RequestBody Farmer farmer) {
        Farmer savedFarmer = farmerService.saveFarmer(farmer);
        return ResponseEntity.status(HttpStatus.CREATED).header("add","Shetkari added").body(farmer);
    }
    
    @GetMapping("/getAllFarmer")
    public List<Farmer> getAllFarmers() {
        return farmerService.findAll();
    }
    
    @GetMapping("/getOnePlayer/{id}")
    public ResponseEntity<Farmer> getFarmerById(@PathVariable Long id) {
        Optional<Farmer> farmer = farmerService.findById(id);
        return farmer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/getFarmerByUsername/{username}")
    public ResponseEntity<Farmer> getFarmerByUsername(@PathVariable String username) {
        Optional<Farmer> farmer = farmerService.findByUsername(username);
        return farmer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
     
     
    @DeleteMapping("/deleteFarmer/{id}")
    public ResponseEntity<Map<String,Object>> deleteFarmer(@PathVariable("id") long id)throws FarmerNotFoundException
    {
    	Map<String,Object> response=farmerService.deleteFarmer(id);
    	return ResponseEntity.ok(response);
    }
    
    @PutMapping("/updateFarmer")
    public ResponseEntity<Farmer> updateFarmer(@RequestBody Farmer farmer) {
        Farmer f= farmerService.updateFarmer(farmer);
        return ResponseEntity.status(HttpStatus.CREATED).header("update","farmer updated").body(farmer);
    }
    
}

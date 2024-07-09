package com.controller;

import com.exception.FarmerNotFoundException;
import com.exception.RetailerNotFoundException;
import com.model.Farmer;
import com.model.Retailer;
import com.service.RetailerService;
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
public class RetailerController {
    @Autowired
    private RetailerService retailerService;
    
    @PostMapping("/retailerLogin")
    public ResponseEntity<Boolean> loginRetailer(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        String role = request.get("role");

        System.out.println("Received login request for user: " + username + " with role: " + role);

        boolean authenticated = false;
        try {
            authenticated = retailerService.authenticate(username, password, role);
        } catch (RetailerNotFoundException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(authenticated);
    }
    
    @PostMapping("/addRetailer")
    public ResponseEntity<Retailer> createRetailer(@RequestBody Retailer retailer) {
        Retailer savedRetailer = retailerService.save(retailer);
        return new ResponseEntity<>(savedRetailer, HttpStatus.CREATED);
    }
    
    @GetMapping("/getAllRetailer")
    public List<Retailer> getAllRetailers() {
        return retailerService.findAll();
    }
    
    @GetMapping("/getRetailerByUsername/{username}")
    public ResponseEntity<Retailer> getRetailerByUsername(@PathVariable String username) {
        Optional<Retailer> retailer = retailerService.findByUsername(username);
        return retailer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/deleteRetailer/{id}")
    public ResponseEntity<Map<String,Object>> deleteRetailer(@PathVariable("id") long id)throws RetailerNotFoundException
    {
    	Map<String,Object> response=retailerService.deleteRetailer(id);
    	return ResponseEntity.ok(response);
    }
    
    @PutMapping("/updateRetailer")
    public ResponseEntity<Retailer> updateRetailer(@RequestBody Retailer retailer)
    {
    	Retailer r= retailerService.updateRetailer(retailer);
        return ResponseEntity.status(HttpStatus.CREATED).header("update","farmer updated").body(r);
    }
     
     /*
    @GetMapping("/{id}")
    public ResponseEntity<Retailer> getRetailerById(@PathVariable Long id) {
        Optional<Retailer> retailer = retailerService.findById(id);
        return retailer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    */
}



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

import com.exception.FarmerNotFoundException;
import com.exception.LabourNotFoundException;
import com.model.Farmer;
import com.model.Labour;
import com.service.LabourService;

@RestController
@RequestMapping("/agroch")
@CrossOrigin(origins = "http://localhost:4200")
public class LabourController {
	
	@Autowired
	private LabourService labourService;
	
	@PostMapping("/labourLogin")
    public ResponseEntity<Boolean> loginLabour(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        String role = request.get("role");

        System.out.println("Received login request for user: " + username + " with role: " + role);

        boolean authenticated = false;
        try {
            authenticated = labourService.authenticate(username, password, role);
        } catch (LabourNotFoundException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(authenticated);
    }
	
	@PostMapping("/addLabour")
	public ResponseEntity<Labour> addLabour(@RequestBody Labour labour) {
		Labour lab = labourService.addLabour(labour);
        return ResponseEntity.status(HttpStatus.CREATED).header("Add","Resource added").body(lab);
    }
	
	 @GetMapping("/getAllLabour")
	    public List<Labour> getAllLabour() {
	        return labourService.findAll();
     }
	 
	  @GetMapping("/getLabourByUsername/{username}")
	    public ResponseEntity<Labour> getLabourByUsername(@PathVariable String username) {
	        Optional<Labour> labour = labourService.findByUsername(username);
	        return labour.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	    }
	     
	 

       @DeleteMapping("/deleteLabour/{id}")
	    public ResponseEntity<Map<String,Object>> deleteLabour(@PathVariable("id") long id)throws LabourNotFoundException
	    {
	    	Map<String,Object> response=labourService.deleteLabour(id);
	    	return ResponseEntity.ok(response);
	    }
	    
	    @PutMapping("/updateLabour")
	    public ResponseEntity<Labour> updateLabour(@RequestBody Labour labour) {
	    	Labour l= labourService.updateLabour(labour);
	        return ResponseEntity.status(HttpStatus.CREATED).header("update","Labour updated").body(labour);
	    }
}

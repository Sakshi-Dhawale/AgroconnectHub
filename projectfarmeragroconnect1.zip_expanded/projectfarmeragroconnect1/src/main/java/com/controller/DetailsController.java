package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.model.Details;
import com.service.DetailsService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/agroch")
@CrossOrigin(origins = "http://localhost:4200")
public class DetailsController {

    @Autowired
    private DetailsService detailsService;

    @GetMapping("/getAllDetails")
    public List<Details> getAllDetails() {
        return detailsService.getAllDetails();
    }

    @GetMapping("/contractor/{contractor}")
    public List<Details> getDetailsByContractor(@PathVariable String contractor) {
        return detailsService.getDetailsByContractor(contractor);
    }

    @PostMapping
    public Details saveDetails(@RequestParam("image") MultipartFile image,
                               @RequestParam("mobileNumber") String mobileNumber,
                               @RequestParam("description") String description,
                               @RequestParam("contractor") String contractor) throws IOException {
        Details details = new Details();
        details.setMobileNumber(mobileNumber);
        details.setDescription(description);
        details.setContractor(contractor);
        details.setImage(image.getBytes());
        return detailsService.saveDetails(details);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Details details = detailsService.getDetailsById(id);
        if (details != null && details.getImage() != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"image.jpg\"")
                    .body(details.getImage());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/details/{id}")
    public ResponseEntity<Void> deleteDetails(@PathVariable Long id) {
        try {
            detailsService.deleteDetails(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

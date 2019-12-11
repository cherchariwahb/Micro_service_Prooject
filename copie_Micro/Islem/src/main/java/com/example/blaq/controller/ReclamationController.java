package com.example.blaq.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.blaq.model.Reclamation;
import com.example.blaq.model.User;
import com.example.blaq.repository.UserRepository;
import com.example.blaq.repository.ReclamationRepository;
import com.example.blas.exception.ResourceNotFoundException;
import javax.validation.Valid;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



@RestController
@RequestMapping("/projet")
public class ReclamationController {
	
	@Autowired
    ReclamationRepository ReclamationRepository;
	@Autowired
    UserRepository UserRepository;

	
	@GetMapping("/reclamation")
    public List<Reclamation> getAllReclamations() {
        return ReclamationRepository.findAll();
    }
	 // Create a new Note
    @PostMapping("/reclamation/user/{id}")
    public Reclamation createReclamation(@PathVariable (value = "id") Long userid,
                                 @Valid @RequestBody Reclamation reclamation) {
        return UserRepository.findById(userid).map(user -> {
            reclamation.setUser(user);
            return ReclamationRepository.save(reclamation);
        }).orElseThrow(() -> new ResourceNotFoundException("user " + userid + " not found"));
    }
    // Get a Single Note
    @GetMapping("/reclamation/{id}")
    public Reclamation getReclamationById(@PathVariable(value = "id") Long reclamationid) {
        return ReclamationRepository.findById(reclamationid)
                .orElseThrow(() -> new ResourceNotFoundException("reclamation", "id", reclamationid));
    }
    
    // Update a Note
    @PutMapping("/reclamation/{id}/user/{iduser}")
    public Reclamation updateReclamation(@PathVariable(value = "id") Long reclamationid,
    		                              @PathVariable(value = "iduser") Long userid,
                                            @Valid @RequestBody Reclamation reclamationdetails) {

    	Reclamation reclamation = ReclamationRepository.findById(reclamationid)
                .orElseThrow(() -> new ResourceNotFoundException("reclamation", "id", reclamationid));

    	reclamation.setTitre(((reclamationdetails.getTitre()))); 
    	reclamation.setDescription((reclamationdetails.getDescription()));
    	   return UserRepository.findById(userid).map(user -> {
               reclamation.setUser(user);
               return ReclamationRepository.save(reclamation);
           }).orElseThrow(() -> new ResourceNotFoundException("user " + userid + " not found"));

    }

    // Delete a Note
    @DeleteMapping("/reclamation/{id}")
    public ResponseEntity<?> deleteReclamation(@PathVariable(value = "id") Long reclamationid) {
    	Reclamation reclamation = ReclamationRepository.findById(reclamationid)
                .orElseThrow(() -> new ResourceNotFoundException("reclamation", "id", reclamationid));

    	ReclamationRepository.delete(reclamation);

        return ResponseEntity.ok().build();
    }
    @GetMapping("/users/{id}/reclamation")
    public Page<Reclamation> getAllReclamationsByUserId(@PathVariable (value = "id") Long userid,
                                                Pageable pageable) {
        return ReclamationRepository.findByUserId(userid, pageable);
    }


}

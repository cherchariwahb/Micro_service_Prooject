package com.example.blaq.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blaq.model.Type;

import com.example.blaq.repository.TypeRepository;
import com.example.blas.exception.ResourceNotFoundException;;

@RestController
@RequestMapping("/projet")
public class TypeController {
	@Autowired
	TypeRepository typeRepository;

	@GetMapping("/types")
	public List<Type> getAllTypes() {
		return typeRepository.findAll();
	}

	// Create a new Note
	@PostMapping("/types")
	public Type createType(@Valid @RequestBody Type type) {
		return typeRepository.save(type);
	}

	// Get a Single Note
	@GetMapping("/types/{id}")
	public Type getTypeById(@PathVariable(value = "id") Long typeId) {
		return typeRepository.findById(typeId)
				.orElseThrow(() -> new ResourceNotFoundException("Type", "id", typeId));
	}
	
    // Delete a Note
    @DeleteMapping("/types/{id}")
    public ResponseEntity<?> deleteType(@PathVariable(value = "id") Long typeId) {
    	Type type = typeRepository.findById(typeId)
                .orElseThrow(() -> new ResourceNotFoundException("Type", "id", typeId));

    	typeRepository.delete(type);

        return ResponseEntity.ok().build();
    }
    
    // Update a Note
    @PutMapping("/types/{id}")
    public Type updateType(@PathVariable(value = "id") Long typeId,
                                            @Valid @RequestBody Type typeDetails) {

    	Type type = typeRepository.findById(typeId)
                .orElseThrow(() -> new ResourceNotFoundException("Type", "id", typeId));

        type.setNom(typeDetails.getNom());
        type.setDescription(typeDetails.getDescription());

        Type updatedNote = typeRepository.save(type);
        return updatedNote;
    }

}

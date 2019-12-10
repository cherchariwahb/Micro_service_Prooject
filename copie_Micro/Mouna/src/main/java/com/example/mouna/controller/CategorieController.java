package com.example.mouna.controller;

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

import com.example.mouna.exception.ResourceNotFoundException;
import com.example.mouna.model.Categorie;
import com.example.mouna.repository.CategorieRepository;;

@RestController
@RequestMapping("/projet")
public class CategorieController {

	@Autowired
	CategorieRepository categorieRepository;

	@GetMapping("/categories")
	public List<Categorie> getAllCategories() {
		return categorieRepository.findAll();
	}

	// Create a new Note
	@PostMapping("/categories")
	public Categorie createCategorie(@Valid @RequestBody Categorie categorie) {
		return categorieRepository.save(categorie);
	}

	// Get a Single Note
	@GetMapping("/categories/{id}")
	public Categorie getCategorieById(@PathVariable(value = "id") Long categorieId) {
		return categorieRepository.findById(categorieId)
				.orElseThrow(() -> new ResourceNotFoundException("Categorie", "id", categorieId));
	}
	
    // Delete a Note
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> deleteCategorie(@PathVariable(value = "id") Long categorieId) {
    	Categorie categorie = categorieRepository.findById(categorieId)
                .orElseThrow(() -> new ResourceNotFoundException("Categorie", "id", categorieId));

    	categorieRepository.delete(categorie);

        return ResponseEntity.ok().build();
    }
    
    // Update a Note
    @PutMapping("/categories/{id}")
    public Categorie updateCategorie(@PathVariable(value = "id") Long categorieId,
                                            @Valid @RequestBody Categorie categorieDetails) {

    	Categorie categorie = categorieRepository.findById(categorieId)
                .orElseThrow(() -> new ResourceNotFoundException("Categorie", "id", categorieId));

        categorie.setNom(categorieDetails.getNom());
        categorie.setDescription(categorieDetails.getDescription());

        Categorie updatedNote = categorieRepository.save(categorie);
        return updatedNote;
    }

}

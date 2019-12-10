package com.example.blaq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.blaq.model.Panier;

import com.example.blaq.repository.PanierRepository;

import com.example.blas.exception.ResourceNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projet")
public class PanierController {

	@Autowired
	PanierRepository panierRepository;

	@GetMapping("/paniers")
	public List<Panier> getAllPaniers() {
		return panierRepository.findAll();
	}

	// Create a new Note
	@PostMapping("/paniers")
	public Panier createPanier(@Valid @RequestBody Panier panier) {
		return panierRepository.save(panier);
	}

	// Get a Single Note
	@GetMapping("/paniers/{id}")
	public Panier getPanierById(@PathVariable(value = "id") Long panierId) {
		return panierRepository.findById(panierId)
				.orElseThrow(() -> new ResourceNotFoundException("Panier", "id", panierId));
	}

	// Update a Note
	@PutMapping("/paniers/{id}")
	public Panier updatePanier(@PathVariable(value = "id") Long panierId, @Valid @RequestBody Panier panierDetails) {

		Panier panier = panierRepository.findById(panierId)
				.orElseThrow(() -> new ResourceNotFoundException("Panier", "id", panierId));

		panier.setDescription(((panierDetails.getDescription())));
		panier.setMontant(((panierDetails.getMontant())));
		panier.setPaye(((panierDetails.getPaye())));

		Panier updatedPanier = panierRepository.save(panier);
		return updatedPanier;
	}

	// Delete a Note
	@DeleteMapping("/paniers/{id}")
	public ResponseEntity<?> deletePanier(@PathVariable(value = "id") Long panierId) {
		Panier panier = panierRepository.findById(panierId)
				.orElseThrow(() -> new ResourceNotFoundException("Panier", "id", panierId));

		panierRepository.delete(panier);

		return ResponseEntity.ok().build();
	}

}

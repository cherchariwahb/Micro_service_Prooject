package com.example.mouna.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.mouna.exception.ResourceNotFoundException;
import com.example.mouna.model.Produit;
import com.example.mouna.repository.CategorieRepository;
import com.example.mouna.repository.ProduitRepository;

import javax.validation.Valid;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



@RestController
@RequestMapping("/projet")
public class ProduitController {
	
	@Autowired
    ProduitRepository produitRepository;
	
	@Autowired
	CategorieRepository categorieRepository;
	
	@GetMapping("/produits")
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }
	 // Create a new Note
    @PostMapping("/produits")
    public Produit createProduit(@Valid @RequestBody Produit produit) {
        return produitRepository.save(produit);
    }
    // Get a Single Note
    @GetMapping("/produits/{id}")
    public Produit getProduitById(@PathVariable(value = "id") Long produitId) {
        return produitRepository.findById(produitId)
                .orElseThrow(() -> new ResourceNotFoundException("Produit", "id", produitId));
    }
    
    // Update a Note
    @PutMapping("/produits/{id}")
    public Produit updateProduit(@PathVariable(value = "id") Long produitId,
                                            @Valid @RequestBody Produit produitDetails) {

        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new ResourceNotFoundException("Produit", "id", produitId));

        produit.setLabel((produitDetails.getLabel()));
        produit.setDescription((produitDetails.getDescription()));
        produit.setPrix((produitDetails.getPrix()));

        Produit updatedNote = produitRepository.save(produit);
        return updatedNote;
    }

    // Delete a Note
    @DeleteMapping("/produits/{id}")
    public ResponseEntity<?> deleteProduit(@PathVariable(value = "id") Long produitId) {
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new ResourceNotFoundException("Produit", "id", produitId));

        produitRepository.delete(produit);

        return ResponseEntity.ok().build();
    }
    @GetMapping("/categories/{id}/produits")
    public Page<Produit> getAllProduitsByCategorieId(@PathVariable (value = "id") Long categorieId,
                                                Pageable pageable) {
        return produitRepository.findByCategorieId(categorieId, pageable);
    }
    @PostMapping("/categories/{id}/produits")
    public Produit createProduit(@PathVariable (value = "id") Long categorieId,
                                 @Valid @RequestBody Produit produit) {
        return categorieRepository.findById(categorieId).map(categorie -> {
            produit.setCategorie(categorie);
            return produitRepository.save(produit);
        }).orElseThrow(() -> new ResourceNotFoundException("CategorieId " + categorieId + " not found"));
    }
    @PutMapping("/categories/{id}/produits/{produitId}")
    public Produit updateProduit(@PathVariable (value = "id") Long categorieId,
                                 @PathVariable (value = "produitId") Long produitId,
                                 @Valid @RequestBody Produit produitRequest) {
        if(!categorieRepository.existsById(categorieId)) {
            throw new ResourceNotFoundException("PostId " + categorieId + " not found");
        }

        return produitRepository.findById(produitId).map(produit -> {
        	 produit.setLabel(produitRequest.getLabel());
             produit.setDescription((produitRequest.getDescription()));
             produit.setPrix((produitRequest.getPrix()));
            return produitRepository.save(produit);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + produitId + "not found"));
    }
    
    @DeleteMapping("/categories/{id}/produits/{produitId}")
    public ResponseEntity<?> deleteProduit(@PathVariable (value = "id") Long categorieId,
                              @PathVariable (value = "produitId") Long produitId) {
        return produitRepository.findByIdAndCategorieId(produitId, categorieId).map(produit -> {
        	produitRepository.delete(produit);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + produitId + " and postId " + categorieId));
    }

}

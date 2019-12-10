package com.example.mouna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.mouna.exception.ResourceNotFoundException;
import com.example.mouna.model.Panier;
import com.example.mouna.model.Produit;
import com.example.mouna.model.User;
import com.example.mouna.repository.PanierRepository;
import com.example.mouna.repository.ProduitRepository;
import com.example.mouna.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projet")
public class PanierController {

	@Autowired
	PanierRepository panierRepository;
	
	@Autowired
	ProduitRepository produitRepository;

	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/paniers")
	public List<Panier> getAllPaniers() {
		return panierRepository.findAll();
	}

	// Create a new Note
	@PostMapping("/paniers")
	public Panier createPanier(@Valid @RequestBody Panier panier) {
		return panierRepository.save(panier);
	}
	
	//OK
	
	@GetMapping("/user/{id}/panier")
	public Page<Panier> getAllProduitsByIduser(@PathVariable (value = "id") Long Iduser,Pageable pageable) {
    return panierRepository.findByUserId(Iduser,pageable);
	}
	 // OK
	@PostMapping("/user/{id}/produits/{id_P}")
	    public Panier createPanier(@PathVariable (value = "id") Long userId,
	    		@PathVariable (value = "id_P") Long produitId,
	    		@Valid @RequestBody Panier panier) {
	         userRepository.findById(userId).map(user -> {
	            panier.setUser(user);
	            return panier ;
	        });
	        return produitRepository.findById(produitId).map(produit -> {
	            panier.setIdproduit(produit);
	            return panierRepository.save(panier);
	        }).orElseThrow(() -> new ResourceNotFoundException("CategorieId " + userId + " not found"));
	    }
	
	// OK
		@PutMapping("/userr/{id}/prod/{id_P1}")
			    public Panier updatePanier(@PathVariable (value = "id") Long userId,
			    	@PathVariable (value = "id_P1") Long produitId,
			    		@Valid @RequestBody Panier panier) 
			    {
			        
			        return userRepository.findById(userId).map(produit -> {
			            panier.setIdproduit(panier.getIdproduit());
			          return panierRepository.save(panier);
			      }).orElseThrow(() -> new ResourceNotFoundException("CategorieId " + userId + " not found"));
			   }
			 
			
	
	// OK
		//@PutMapping("/userr/{id}/prod/{id_P}")
		   // public Panier updatePanier(@PathVariable (value = "id") Long userId,
		    //		@PathVariable (value = "id_P") Long produitId,
		    	//	@Valid @RequestBody Panier panier) {
		         //userRepository.findById(userId).map(user -> {
		           // panier.setId(panier.getUser());
		            //return panier ;
		        //});
		        //return produitRepository.findById(produitId).map(produit -> {
		          //  panier.setId(panier.getIdproduit());
		            //return panierRepository.save(panier);
		      //  }).orElseThrow(() -> new ResourceNotFoundException("CategorieId " + userId + " not found"));
		   // }
	
		
	@GetMapping("/paniers/{id}")
	public Panier getPanierById(@PathVariable(value = "id") Long panierId) {
		return panierRepository.findById(panierId)
				.orElseThrow(() -> new ResourceNotFoundException("Panier", "id", panierId));
	}
	

	

		@DeleteMapping("/user/{id}/prod/{prodId}")
    public ResponseEntity<?> deletePanier(@PathVariable (value = "id") Long userId,
    		@Valid @PathVariable (value = "prodId") Long produitId) {
        return panierRepository.findByIdproduitAndUser(produitId, userId).map(panier -> {
        	panierRepository.delete(panier);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(" "));
    } 

}

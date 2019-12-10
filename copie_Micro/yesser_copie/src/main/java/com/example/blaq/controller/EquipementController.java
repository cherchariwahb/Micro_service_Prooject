package com.example.blaq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.blaq.model.Equipement;
import com.example.blaq.model.Panier;
import com.example.blaq.model.Produit;
import com.example.blaq.repository.EquipementRepository;
import com.example.blaq.repository.TypeRepository;
import com.example.blas.exception.ResourceNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projet")
public class EquipementController {

	@Autowired
	EquipementRepository equipementRepository;
	TypeRepository typeRepository;

	@GetMapping("/equipements")
	public List<Equipement> getAllEquipements() {
		return equipementRepository.findAll();
	}

	// Create a new Note
	@PostMapping("/equipements")
	public Equipement createEquipement(@Valid @RequestBody Equipement equipement) {
		return equipementRepository.save(equipement);
	}

	// Get a Single Note
	@GetMapping("/equipements/{id}")
	public Equipement getEquipementById(@PathVariable(value = "id") Long equipementId) {
		return equipementRepository.findById(equipementId)
				.orElseThrow(() -> new ResourceNotFoundException("Equipement", "id", equipementId));
	}
	
	// Update a Note
		@PutMapping("/equipements/{id}")
		public Equipement updateEquipement(@PathVariable(value = "id") Long equipementId, @Valid @RequestBody Equipement equipementDetails) {

			Equipement equipement = equipementRepository.findById(equipementId)
					.orElseThrow(() -> new ResourceNotFoundException("Panier", "id", equipementId));
			equipement.setLabel(equipementDetails.getLabel());
			equipement.setDescription(((equipementDetails.getDescription())));
			equipement.setQuantite(equipementDetails.getQuantite());
			equipement.setDisponible(equipementDetails.getDisponible());

			Equipement updatedPanier = equipementRepository.save(equipement);
			return updatedPanier;
		}

		// Delete a Note
		@DeleteMapping("/equipements/{id}")
		public ResponseEntity<?> deleteEquipement(@PathVariable(value = "id") Long equipementId) {
			Equipement equipement = equipementRepository.findById(equipementId)
					.orElseThrow(() -> new ResourceNotFoundException("User", "id", equipementId));

			equipementRepository.delete(equipement);

			return ResponseEntity.ok().build();
		}
		@GetMapping("/types/{id}/equipements")
	    public Page<Equipement> getAllEquipementsByTypeId(@PathVariable (value = "id") Long typeId,
	                                                Pageable pageable) {
	        return equipementRepository.findByTypeId(typeId, pageable);
	    }
	    @PostMapping("/types/{id}/equipements")
	    public Equipement createEquipement(@PathVariable (value = "id") Long typeId,
	                                 @Valid @RequestBody Equipement equipement) {
	        return typeRepository.findById(typeId).map(type -> {
	            equipement.setType(type);
	            return equipementRepository.save(equipement);
	        }).orElseThrow(() -> new ResourceNotFoundException("TypeId " + typeId + " not found"));
	    }
	    @PutMapping("/types/{id}/equipements/{equipementId}")
	    public Equipement updateEquipement(@PathVariable (value = "id") Long typeId,
	                                 @PathVariable (value = "equipementId") Long equipementId,
	                                 @Valid @RequestBody Equipement equipementRequest) {
	        if(!typeRepository.existsById(typeId)) {
	            throw new ResourceNotFoundException("PostId " + typeId + " not found");
	        }

	        return equipementRepository.findById(equipementId).map(equipement -> {
	        	 equipement.setDescription((equipementRequest.getDescription()));
	             equipement.setDisponible((equipementRequest.getDisponible()));
	             equipement.setLabel((equipementRequest.getLabel()));
	             equipement.setQuantite((equipementRequest.getQuantite()));
	            return equipementRepository.save(equipement);
	        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + equipementId + "not found"));
	    }
	    
	    @DeleteMapping("/types/{id}/equipements/{equipementId}")
	    public ResponseEntity<?> deleteEquipement(@PathVariable (value = "id") Long typeId,
	                              @PathVariable (value = "equipementId") Long equipementId) {
	        return equipementRepository.findByIdAndTypeId(equipementId, typeId).map(equipement -> {
	        	equipementRepository.delete(equipement);
	            return ResponseEntity.ok().build();
	        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + equipementId + " and postId " + typeId));
	    }

}

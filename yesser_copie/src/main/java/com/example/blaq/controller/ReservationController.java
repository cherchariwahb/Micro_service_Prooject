package com.example.blaq.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blaq.model.Equipement;
import com.example.blaq.model.Reservation;

import com.example.blaq.repository.ReservationRepository;
import com.example.blas.exception.ResourceNotFoundException;


@RestController
@RequestMapping("/projet")
public class ReservationController {
	@Autowired
	ReservationRepository reservationRepository;

	@GetMapping("/reservations")
	public List<Reservation> getAllReservation() {
		return reservationRepository.findAll();
	}
	@PostMapping("/reservations")
	public Reservation createReservation(@Valid @RequestBody Reservation reservation) {
		return reservationRepository.save(reservation);
	}
	// Get a Single Note
		@GetMapping("/reservations/{id}")
		public Reservation getReservationById(@PathVariable(value = "id") Long reservationId) {
			return reservationRepository.findById(reservationId)
					.orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", reservationId));
		}
		
		  // Delete a Note
	    @DeleteMapping("/reservations/{id}")
	    public ResponseEntity<?> deleteReservation(@PathVariable(value = "id") Long reservationId) {
	    	Reservation reservation = reservationRepository.findById(reservationId)
	                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", reservationId));

	    	reservationRepository.delete(reservation);

	        return ResponseEntity.ok().build();
	    }
	 // Update a Note
	    @PutMapping("/reservations/{id}")
	    public Reservation updateReservation(@PathVariable(value = "id") Long reservationId,
	                                            @Valid @RequestBody Reservation reservationDetails) {

	    	Reservation reservation = reservationRepository.findById(reservationId)
	                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", reservationId));

	    	reservation.setDatedeb(reservationDetails.getDatedeb());
	    	reservation.setDatefin(reservationDetails.getDatefin());

	    	Reservation updatedNote = reservationRepository.save(reservation);
	        return updatedNote;
	    }
	    
}

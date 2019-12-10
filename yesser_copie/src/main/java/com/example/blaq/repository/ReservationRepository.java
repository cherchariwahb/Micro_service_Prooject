package com.example.blaq.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blaq.model.Equipement;
import com.example.blaq.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
	Page<Reservation> findByEquipementId(Long equipementId, Pageable pageable);
    Optional<Reservation> findByIdAndEquipementId(Long id, Long equipementId);
	

}

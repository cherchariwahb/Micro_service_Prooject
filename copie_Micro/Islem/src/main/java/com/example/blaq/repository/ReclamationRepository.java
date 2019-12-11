package com.example.blaq.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


import com.example.blaq.model.Reclamation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {
	
	Page<Reclamation> findByUserId(Long id, Pageable pageable);

}

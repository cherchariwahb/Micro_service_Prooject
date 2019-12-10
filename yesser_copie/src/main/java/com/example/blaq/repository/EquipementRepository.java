package com.example.blaq.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.blaq.model.Equipement;
import com.example.blaq.model.Produit;

@Repository
public interface EquipementRepository extends JpaRepository<Equipement,Long> {
	Page<Equipement> findByTypeId(Long typeId, Pageable pageable);
    Optional<Equipement> findByIdAndTypeId(Long id, Long typeId);
    
}

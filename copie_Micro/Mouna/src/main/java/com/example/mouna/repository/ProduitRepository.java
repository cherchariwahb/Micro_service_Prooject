package com.example.mouna.repository;

import org.springframework.stereotype.Repository;

import com.example.mouna.model.Categorie;
import com.example.mouna.model.Produit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
	
	Page<Produit> findByCategorieId(Long categorieId, Pageable pageable);
    Optional<Produit> findByIdAndCategorieId(Long id, Long categorieId);
   

}

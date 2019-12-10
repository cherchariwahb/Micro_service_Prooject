package com.example.blaq.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blaq.model.Categorie;
import com.example.blaq.model.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
	
	Page<Produit> findByCategorieId(Long categorieId, Pageable pageable);
    Optional<Produit> findByIdAndCategorieId(Long id, Long categorieId);

}

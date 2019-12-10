package com.example.blaq.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blaq.model.Stock;
import com.example.blaq.model.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
	
	Page<Stock> findByProduitId(Long categorieId, Pageable pageable);
    Optional<Stock> findByIdAndProduitId(Long id, Long categorieId);

}

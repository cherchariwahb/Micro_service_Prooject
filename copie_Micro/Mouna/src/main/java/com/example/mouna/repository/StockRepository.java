package com.example.mouna.repository;

import org.springframework.stereotype.Repository;

import com.example.mouna.model.Produit;
import com.example.mouna.model.Stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
	
	Page<Stock> findByProduitId(Long categorieId, Pageable pageable);
    Optional<Stock> findByIdAndProduitId(Long id, Long categorieId);

}

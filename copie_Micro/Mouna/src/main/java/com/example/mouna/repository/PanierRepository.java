package com.example.mouna.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mouna.model.Panier;
import com.example.mouna.model.Produit;
import com.example.mouna.model.User;

import java.util.Optional;

@Repository
public interface PanierRepository extends JpaRepository<Panier,Long> {
	
	 Page<Panier> findByUserId(Long id, Pageable pageable);
	Optional<Panier> findByIdproduitAndUser(Long id, Long userId);
}

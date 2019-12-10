package com.example.blaq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.blaq.model.Panier;

@Repository
public interface PanierRepository extends JpaRepository<Panier,Long> {

}

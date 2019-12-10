package com.example.blaq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.blaq.model.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie,Long> {

}

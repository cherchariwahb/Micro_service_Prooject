package com.example.mouna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mouna.model.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie,Long> {

}

package com.example.blaq.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blaq.model.Equipement;
import com.example.blaq.model.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long>  {
	
	
}

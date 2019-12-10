package com.example.mouna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mouna.model.Equipement;

@Repository
public interface EquipementRepository extends JpaRepository<Equipement,Long> {

}

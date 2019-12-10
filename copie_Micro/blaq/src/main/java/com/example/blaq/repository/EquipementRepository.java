package com.example.blaq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.blaq.model.Equipement;

@Repository
public interface EquipementRepository extends JpaRepository<Equipement,Long> {

}

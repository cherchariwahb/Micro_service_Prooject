package com.example.mouna.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mouna.model.Rate;

import java.util.Optional;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
	
    Page<Rate> findByPostId(Long postId, Pageable pageable);
    Optional<Rate> findByIdAndPostId(Long id, Long postId);
}
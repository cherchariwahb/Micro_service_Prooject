package com.example.blaq.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


import com.example.blaq.model.Task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	
	Page<Task> findByUserId(Long id, Pageable pageable);

}

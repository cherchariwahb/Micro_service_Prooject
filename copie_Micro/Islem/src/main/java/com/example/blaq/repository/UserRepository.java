package com.example.blaq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.blaq.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
         User findByMail(String mail) ; 
}

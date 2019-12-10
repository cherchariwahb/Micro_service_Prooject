package com.example.mouna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.mouna.exception.ResourceNotFoundException;
import com.example.mouna.model.User;
import com.example.mouna.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projet")
public class UserController {
	@Autowired
	UserRepository userRepository;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// Create a new Note
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}

	// Get a Single Note
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable(value = "id") Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
	}

	// Update a Note
	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		user.setNom(((userDetails.getNom())));
		user.setPrenom(((userDetails.getPrenom())));
		user.setMail(((userDetails.getMotDePasse())));
		user.setRole((userDetails.getRole()));
		user.setMotDePasse(userDetails.getMotDePasse());

		User updatedUser = userRepository.save(user);
		return updatedUser;
	}

	// Delete a Note
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		userRepository.delete(user);

		return ResponseEntity.ok().build();
	}

}

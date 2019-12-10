package com.example.mouna.controller;

import com.example.mouna.exception.ResourceNotFoundException;
import com.example.mouna.model.Post;
import com.example.mouna.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/projet")
public class PostController {

    @Autowired
    PostRepository postRepository;

    // Get All Posts
    @GetMapping("/posts")
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    // Create a new Post
    @PostMapping("/posts")
    public Post createPost(@Valid @RequestBody Post post) {
        return postRepository.save(post);
    }
    
    // Get a Single Post
    @GetMapping("/posts/{id}")
    public Post getPostById(@PathVariable(value = "id") Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    // Update a Post
    @PutMapping("/posts/{id}")
    public Post updatePost(@PathVariable(value = "id") Long postId,
                                            @Valid @RequestBody Post postDetails) {

    	Post post = postRepository.findById(postId)
        		.orElseThrow(() -> new ResourceNotFoundException("not found"));

    	post.setTitle(postDetails.getTitle());
    	post.setDescription(postDetails.getDescription());
    	post.setContent(postDetails.getContent());
    	
    	Post updatedPost = postRepository.save(post);
        return updatedPost;
    }


    // Delete a Post
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long postId) {
    	Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

    	postRepository.delete(post);

        return ResponseEntity.ok().build();
    }

}

package com.example.mouna.controller;

import com.example.mouna.exception.ResourceNotFoundException;
import com.example.mouna.model.Comment;
import com.example.mouna.model.Rate;
import com.example.mouna.repository.CommentRepository;
import com.example.mouna.repository.PostRepository;
import com.example.mouna.repository.RateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/projet")
public class RateController {

    @Autowired
    RateRepository rateRepository;

    @Autowired
    PostRepository postRepository;

    @GetMapping("/posts/{postId}/rates")
    public Page<Rate> getAllRatesByPostId(@PathVariable (value = "postId") Long postId,
                                                Pageable pageable) {
        return rateRepository.findByPostId(postId, pageable);
    }

    @PostMapping("/posts/{postId}/rates")
    public Rate createRate(@PathVariable (value = "postId") Long postId,
                                 @Valid @RequestBody Rate rate) {
        return postRepository.findById(postId).map(post -> {
            rate.setPost(post);
            return rateRepository.save(rate);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

    @PutMapping("/posts/{postId}/rates/{rateId}")
    public Rate updaterate(@PathVariable (value = "postId") Long postId,
                                 @PathVariable (value = "commentId") Long rateId,
                                 @Valid @RequestBody Rate rateRequest) {
        if(!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }

        return rateRepository.findById(rateId).map(rate -> {
            rate.setRating(rateRequest.getRating());
            return rateRepository.save(rate);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + rateId + "not found"));
    }

    @DeleteMapping("/posts/{postId}/Rates/{rateId}")
    public ResponseEntity<?> deleteRate(@PathVariable (value = "postId") Long postId,
                              @PathVariable (value = "rateId") Long rateId) {
        return rateRepository.findByIdAndPostId(rateId, postId).map(rate -> {
        	rateRepository.delete(rate);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + rateId + " and postId " + postId));
    }
}
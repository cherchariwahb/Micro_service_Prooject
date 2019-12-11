package com.example.blaq.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.blaq.model.Reclamation;
import com.example.blaq.model.Task;
import com.example.blaq.model.User;
import com.example.blaq.repository.UserRepository;
import com.example.blaq.repository.ReclamationRepository;
import com.example.blaq.repository.TaskRepository;
import com.example.blas.exception.ResourceNotFoundException;
import javax.validation.Valid;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



@RestController
@RequestMapping("/projet")
public class TaskController {
	
	@Autowired
    TaskRepository TaskRepository;
	@Autowired
    UserRepository UserRepository;

	
	@GetMapping("/task")
    public List<Task> getAllTasks() {
        return TaskRepository.findAll();  
    }
	 // Create a new Note
    @PostMapping("/task/user/{id}")
    public Task createReclamation(@PathVariable (value = "id") Long userid,
                                 @Valid @RequestBody Task task) {
        return UserRepository.findById(userid).map(user -> {
            task.setUser(user);
            return TaskRepository.save(task);
        }).orElseThrow(() -> new ResourceNotFoundException("user " + userid + " not found"));
    }
    // Get a Single Note
    @GetMapping("/task/{id}")
    public Task getReclamationById(@PathVariable(value = "id") Long taskid) {
        return TaskRepository.findById(taskid)
                .orElseThrow(() -> new ResourceNotFoundException("task", "id", taskid));
    }
    
    // Update a Note
    @PutMapping("/task/{id}/user/{iduser}")
    public Task updateReclamation(@PathVariable(value = "id") Long taskid,
    		                              @PathVariable(value = "iduser") Long userid,
                                            @Valid @RequestBody Task Taskdetails) {

    	Task Task = TaskRepository.findById(taskid)
                .orElseThrow(() -> new ResourceNotFoundException("reclamation", "id", taskid));

    	Task.setTitre(((Taskdetails.getTitre()))); 
    	Task.setDescription((Taskdetails.getDescription()));
    	   return UserRepository.findById(userid).map(user -> {
    		   Task.setUser(user);
               return TaskRepository.save(Task);
           }).orElseThrow(() -> new ResourceNotFoundException("user " + userid + " not found"));

    }

    // Delete a Note
    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteReclamation(@PathVariable(value = "id") Long taskid) {
    	Task Task = TaskRepository.findById(taskid)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", taskid));

    	TaskRepository.delete(Task);

        return ResponseEntity.ok().build();
    }
    @GetMapping("/users/{id}/task")
    public Page<Task> getAllReclamationsByUserId(@PathVariable (value = "id") Long userid,
                                                Pageable pageable) {
        return TaskRepository.findByUserId(userid, pageable);
    }


}

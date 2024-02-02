package com.example.tasktracker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    private static final int MAX_PAGE_SIZE = 50; // Set your maximum page size

    @Configuration
    public class CorsConfig {

        @Bean
        public CorsFilter corsFilter() {
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            CorsConfiguration config = new CorsConfiguration();
            config.addAllowedOrigin("*"); 
            config.addAllowedHeader("*");
            config.addAllowedMethod("OPTIONS");
            config.addAllowedMethod("GET");
            config.addAllowedMethod("POST");
            config.addAllowedMethod("PUT");
            config.addAllowedMethod("DELETE");
            source.registerCorsConfiguration("/api/**", config);
            return new CorsFilter(source);
        }
    }

    @GetMapping
    public ResponseEntity<Page<Task>> getAllTasks(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        if (size > MAX_PAGE_SIZE) {
            size = MAX_PAGE_SIZE;
        } 
        

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Task> taskPage = taskService.getAllTasks(pageRequest);

        return new ResponseEntity<>(taskPage, HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable String taskId) {
        Task task = taskService.getTaskById(taskId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/findByAssignedTo/{name}")
    public ResponseEntity<List<Task>> getAllTaskByAssignedTo(@PathVariable String name) {
        List<Task> tasks = taskService.getAllTaskByAssignedTo(name);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/findByAssignedBy/{name}")
    public ResponseEntity<List<Task>> getAllTaskByAssignedBy(@PathVariable String name) {
        List<Task> tasks = taskService.getAllTaskByAssignedBy(name);
        
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/findByTitle/{name}")
    public ResponseEntity<List<Task>> getAllTasksByTitle(@PathVariable String name) {
        List<Task> tasks = taskService.getAllTasksByTitle(name);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody @Validated Task task) {
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable String taskId, @RequestBody Task updatedTask) {
        Task updated = taskService.updateTask(taskId, updatedTask);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable String taskId) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

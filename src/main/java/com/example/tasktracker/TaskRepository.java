package com.example.tasktracker;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface TaskRepository extends MongoRepository<Task, String>{
    List<Task> findByTitleIgnoreCaseContaining(String keyword);
    @org.springframework.data.mongodb.repository.Query
    List<Task> findByAssignedToIgnoreCaseContaining(String keyword);
    @org.springframework.data.mongodb.repository.Query
    List<Task> findByAssignedByIgnoreCaseContaining(String keyword);
}

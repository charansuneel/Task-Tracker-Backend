package com.example.tasktracker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    public Task getTaskById(String taskId) {
        
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId));
    }
    public List<Task> getAllTasksByTitle(String title){
        return taskRepository.findByTitleIgnoreCaseContaining(title);
    }

    public List<Task> getAllTaskByAssignedTo(String name){
        
        return taskRepository.findByAssignedToIgnoreCaseContaining(name);
    }
    public List<Task> getAllTaskByAssignedBy(String name){
        return taskRepository.findByAssignedByIgnoreCaseContaining(name);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(String taskId, Task updatedTask) {
        Task existingTask = getTaskById(taskId);

        // Update existingTask properties with updatedTask properties
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setDueDate(updatedTask.getDueDate());
        existingTask.setCompleted(updatedTask.isCompleted());

        return taskRepository.save(existingTask);
    }
    public void deleteTask(String taskId) {
        Task existingTask = getTaskById(taskId);
        taskRepository.delete(existingTask);
    }
}



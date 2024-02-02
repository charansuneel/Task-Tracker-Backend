package com.example.tasktracker;

import java.util.Date;

import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Document(collection = "tasks")
public class Task {
    @Id
    private String id;
    @NotBlank(message = "title should not be empty.")
    private String title;
    
    @NotBlank(message = "description should not be empty.")
    private String description;

    @NotNull(message = "date should not be null.")
    @Future(message  ="date should be from future.")
    private Date dueDate;
    
    private boolean completed;
    @NotBlank(message = "assigned by should not be empty.")
    private String assignedBy;
    @NotBlank(message = "assigned to should not be empty.")
    private String assignedTo;
    

    public String getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public Date getDueDate(){
        return dueDate;
    }
    public boolean isCompleted(){
        return completed;
    }
    public String getAssignedBy(){
        return assignedBy;
    }
    public String getAssignedTo(){
        return assignedTo;
    }

    public void setTitle(String title){
        this.title=title;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public void setDueDate(Date dueDate){
        this.dueDate=dueDate;
    }
    public void setCompleted(boolean completed){
        this.completed=completed;
    }
    public void setAssignedBy(String assignedby){
        this.assignedBy=assignedby;
    }
    public void setAssignedTo(String assignedTo){
        this.assignedTo=assignedTo;
    }
}


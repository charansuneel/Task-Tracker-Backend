# Task-Tracker-Backend
In this project we are creating the rest api for the "Task Tracker" app. 
We will create end points like 
1. get -> http://localhost:8081/api/tasks  -> retrives all tasks
2. post -> http://localhost:8081/api/tasks -> create a task
3. put -> http://localhost:8081/api/tasks/{taskId} -> update the task
4. delete -> http://localhost:8081/api/tasks/{taskId} -> delete the task
5. get -> http://localhost:8081/api/tasks/{taskId} -> retrive the task by id
6. get -> http://localhost:8081/api/tasks/findByTitle/{name} -> retrive the tasks by task name
7. get -> http://localhost:8081/api/tasks/findByAssignedBy/{name} -> retrive the tasks by task assigner name
8. get -> http://localhost:8081/api/tasks//findByAssignedTo/{name} -> retrive the tasks for a particular person
   
### Requirments
1. Java
2. Mongodb
3. Postman

## How to run
1. Run the TaskTrackerApplication.java file of com.example.taskmanager package in the src/main/java folder.
2. And then you can use postman for visualizing the working structure of api.

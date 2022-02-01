package com.spring.controller;

import com.spring.dto.TaskRequest;
import com.spring.dto.TaskResponse;
import com.spring.service.TaskService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<String> addTask(@RequestBody TaskRequest taskRequest) {
        taskService.addTask(taskRequest);
        return new ResponseEntity<>("Task created.", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
    }

    /*@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/tasks/{taskid}")
    public void updateTaskById(@PathVariable("taskid") Long id, @RequestBody TaskEntity taskEntity) {
        taskService.updateTask(id, taskEntity);
    }


    @GetMapping("/tasks/{userid}")
    public List<TaskResponse> listOfTasks(@PathVariable("userid") Long userId) {
        return taskService.getAllTasksByUser(userId);
    }*/

    @GetMapping("/show/{taskId}")
    public TaskResponse getTaskInfo(@PathVariable("taskId") Long taskId) {
        return taskService.getAllTasksByUser(taskId);
    }


}

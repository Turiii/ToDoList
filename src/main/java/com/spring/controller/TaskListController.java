package com.spring.controller;

import com.spring.dao.TaskListEntity;
import com.spring.dto.TaskListRequest;
import com.spring.service.TaskListService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ToDoLists")
public class TaskListController {
    private final TaskListService taskListService;

    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addToDoList(@RequestBody TaskListRequest taskListRequest) {
        taskListService.addTaskList(taskListRequest);
    }

    @GetMapping("/show")
    @ResponseBody
    public List<TaskListEntity> showAllLists() {
        return taskListService.showAllLists();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteToDoList(@PathVariable("id") Long id) {
        taskListService.deleteTaskList(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/ToDoLists/{listid}")
    public void updateToDoListByListId(@PathVariable("listid") Long listId, @RequestBody TaskListEntity newList) {
        taskListService.updateTaskList(listId, newList);
    }
}

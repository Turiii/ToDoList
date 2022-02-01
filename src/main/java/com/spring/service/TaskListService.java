package com.spring.service;

import com.spring.dao.TaskListEntity;
import com.spring.dto.TaskListRequest;
import com.spring.mapper.TaskListMapper;
import com.spring.repository.TaskListRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class TaskListService {

    private final TaskListRepository taskListRepository;
    private final TaskListMapper taskListMapper;

    public void addTaskList(TaskListRequest taskListRequest) {
        taskListRepository.save(taskListMapper.fromDto(taskListRequest));
    }

    public void deleteTaskList(Long id) {
        TaskListEntity taskList = taskListRepository.findById(id).orElseThrow();
        taskListRepository.delete(taskList);
    }

    public void updateTaskList(Long listId, TaskListEntity newList) {
        TaskListEntity oldlist = taskListRepository.findById(listId).orElseThrow(() -> new IllegalArgumentException());
        oldlist.setName(newList.getName());
        oldlist.setList(newList.getList());
        oldlist.setUserOwner(newList.getUserOwner());
        oldlist.setUsersWithAccess(newList.getUsersWithAccess());
        taskListRepository.save(oldlist);
    }

    public List<TaskListEntity> showAllLists(){
        return taskListRepository.findAll();
    }
}

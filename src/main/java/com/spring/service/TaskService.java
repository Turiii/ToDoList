package com.spring.service;

import com.spring.dao.TaskEntity;
import com.spring.dto.TaskRequest;
import com.spring.dto.TaskResponse;
import com.spring.mapper.TaskMapper;
import com.spring.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;

    public TaskService(TaskMapper taskMapper, TaskRepository taskRepository) {
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
    }

    public TaskResponse getAllTasksByUser(Long taskId) {

        TaskEntity taskEntity = taskRepository.findById(taskId)
                .orElseThrow();

        return taskMapper.fromDAO(taskEntity);
    }

    @Transactional
    public void addTask(TaskRequest taskRequest) {
        TaskEntity taskEntity = taskMapper.fromDto(taskRequest);
        taskRepository.save(taskEntity);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }


}

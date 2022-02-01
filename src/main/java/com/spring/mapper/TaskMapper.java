package com.spring.mapper;

import com.spring.dao.*;
import com.spring.dto.CommentResponse;
import com.spring.dto.TaskRequest;
import com.spring.dto.TaskResponse;
import com.spring.repository.CommentRepository;
import com.spring.repository.TaskListRepository;
import com.spring.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class TaskMapper {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final TaskListRepository taskListRepository;


    public TaskEntity fromDto(TaskRequest taskRequest){

        TaskListEntity taskListEntity= taskListRepository
                .findById(taskRequest.getListID())
                .orElseThrow();

        TaskEntity taskEntity=new TaskEntity();
        taskEntity.setTaskName(taskRequest.getTaskName());
        taskEntity.setDescription(taskRequest.getDescription());
        taskEntity.setStatus(assignStatus(taskRequest));
        taskEntity.setPriority(assignPriority(taskRequest));
        taskEntity.setTaskType(assignTaskType(taskRequest));
        taskEntity.setDateOfCreation(Timestamp.from(Instant.now()));
        taskEntity.setDateOfDeadline(Timestamp.from(Instant.now().plus(10, ChronoUnit.DAYS)));
        taskEntity.setComments(Collections.emptyList());
        taskEntity.setTaskOwnerId(assignUser(taskRequest));
        taskEntity.setTaskListEntity(taskListEntity);

        return taskEntity;

    }

    public TaskResponse fromDAO(TaskEntity taskEntity) {

        UserEntity userEntity = userRepository.findById(taskEntity.getTaskOwnerId().getId())
                .orElseThrow();

        List<CommentResponse> commentResponses=commentRepository
                .findAllByTaskEntity(taskEntity.getId())
                .stream()
                .map(commentMapper::fromDAO)
                .collect(Collectors.toList());


        return TaskResponse.builder()
                .taskOwner(userEntity.getUsername())
                .taskType(taskEntity.getTaskType().toString())
                .taskName(taskEntity.getTaskName())
                .status(taskEntity.getStatus().toString())
                .priority(taskEntity.getPriority().toString())
                .description(taskEntity.getDescription())
                .build();

    }

    UserEntity assignUser(TaskRequest taskRequest){

        return userRepository.findById(taskRequest.getOwnerID())
                .orElseThrow(()->new UsernameNotFoundException("There is no user with given id"));
    }

    Priority assignPriority(TaskRequest taskRequest) {
        Priority taskEnum = null;
        for (Priority priority : Priority.values()) {
            if (priority.getPriorityType().equals(taskRequest.getPriority())){
                taskEnum = priority;
            }
        }
        return taskEnum;
    }


    Status assignStatus(TaskRequest taskRequest) {
        Status taskEnum = null;
        for (Status status : Status.values()) {
            if (status.getStatusType().equals(taskRequest.getStatus())){
                taskEnum = status;
            }
        }
        return taskEnum;
    }

    TaskType assignTaskType(TaskRequest taskRequest) {
        TaskType taskEnum = null;
        for (TaskType taskType : TaskType.values()) {
            if (taskType.getTaskType().equals(taskRequest.getTaskType())){
                taskEnum = taskType;
            }
        }
        return taskEnum;
    }
}

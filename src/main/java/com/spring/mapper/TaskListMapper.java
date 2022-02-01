package com.spring.mapper;

import com.spring.dao.TaskListEntity;
import com.spring.dao.UserEntity;
import com.spring.dto.TaskListRequest;
import com.spring.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TaskListMapper {

    private final UserRepository userRepository;

    public TaskListEntity fromDto(TaskListRequest taskListRequest){

        UserEntity userOwner=userRepository.findById(taskListRequest.getOwnerId())
                .orElseThrow();

        TaskListEntity taskListEntity=new TaskListEntity();
        taskListEntity.setName(taskListRequest.getName());
        taskListEntity.setUserOwner(userOwner);

        return taskListEntity;

    }
}

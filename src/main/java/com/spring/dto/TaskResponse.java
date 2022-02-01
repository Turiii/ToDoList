package com.spring.dto;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TaskResponse {

    private String taskName;
    private String description;
    private String taskOwner;
    private String status;
    private String taskType;
    private String priority;

}

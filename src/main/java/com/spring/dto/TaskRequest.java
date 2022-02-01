package com.spring.dto;

import com.spring.dao.Status;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TaskRequest {

    private Long ownerID;
    private Long listID;
    private String taskName;
    private String description;
    private Status status;
    private String taskType;
    private String priority;

}

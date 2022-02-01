package com.spring.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class TaskListRequest {

    private String name;
    private Long ownerId;

}

package com.spring.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class CommentRequest {

    private String content;
    private Long owner_id;
    private Long task_id;

}

package com.spring.dto;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
public class CommentResponse {

    private String owner;
    private Timestamp dateOfCreation;
    private Timestamp dateOfEdition;
    private String content;

}

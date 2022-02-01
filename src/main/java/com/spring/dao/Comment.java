package com.spring.dao;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;import java.sql.Timestamp;

@Entity
@Table(name = "comments")
@Data
public class Comment implements Serializable {

    public Comment() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @Column(name="comment_owner_id")
    Long commentOwner;


    @JoinColumn(name = "task_id")
    Long taskEntity;

    @Column(name = "date_created")
    Timestamp dateOfCreation;

    @Column(name = "date_edited")
    Timestamp dateOfEdition;

    @Column(name = "content")
    String commentContent;
}

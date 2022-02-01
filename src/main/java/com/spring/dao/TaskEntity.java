package com.spring.dao;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "task")
@Data
public class TaskEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @Column(name = "task_name")
    private String taskName;

    private String description;


    @ManyToOne
    @JoinColumn(name = "task_owner_id")
    private UserEntity taskOwnerId;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status cannot be null!")
    private Status status;

    @Column(name = "task_type")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Task type cannot be null!")
    private TaskType taskType;

    @Column(name = "date_created")
    private Timestamp dateOfCreation;

    @Column(name = "date_edited")
    private Timestamp dateOfEdition;

    @Column(name = "date_deadline")
    private Timestamp dateOfDeadline;


    @Enumerated(EnumType.STRING)
    @NotNull(message = "Task type cannot be null!")
    private Priority priority;

    @OneToMany(mappedBy = "taskEntity")
    private List<Comment> comments;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private TaskListEntity taskListEntity;
}

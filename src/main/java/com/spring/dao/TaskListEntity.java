package com.spring.dao;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "task_list")
public class TaskListEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity userOwner;

    @OneToMany(mappedBy = "taskListEntity")
    private List<TaskEntity>list;

    @ManyToMany
    @JoinTable(
            name = "list_with_user_access",
            joinColumns = { @JoinColumn(name = "list_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private List<UserEntity> usersWithAccess;
}

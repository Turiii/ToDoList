package com.spring.dao;

public enum TaskType {

    HOUSE("HOUSE"),
    WORK("WORK"),
    SHOPPING("SHOPPING"),
    VISITS("VISITS");


    private final String taskType;


    TaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskType() {
        return taskType;
    }
}

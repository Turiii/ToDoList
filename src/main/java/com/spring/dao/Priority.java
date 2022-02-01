package com.spring.dao;

public enum Priority {
    LOW("LOW"),
    HIGH("HIGH"),
    MEDIUM("MEDIUM");

    private final String priorityType;

    Priority(String priorityType) {
        this.priorityType = priorityType;
    }

    public String getPriorityType() {
        return priorityType;
    }
}

package com.spring.dao;

public enum Status {

    TO_D0("TO DO"),
    IN_PROGRESS("IN PROGRESS"),
    DONE("DONE"),
    POSTPONED("POSTPONED");


    private final String statusType;

    Status(String statusType) {
        this.statusType = statusType;
    }

    public String getStatusType() {
        return statusType;
    }
}

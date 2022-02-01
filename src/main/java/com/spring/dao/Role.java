package com.spring.dao;

public enum Role {

    USER("USER"),
    ADMIN("ADMIN");

    private final String roleType;

    Role(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleType() {
        return roleType;
    }
}

package com.example.mtbs.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Role {
    THEATER_OWNER, USER;

    @JsonCreator
    public static Role fromString(String value) {
        return Role.valueOf(value.toUpperCase());
    }
}

package com.java.dto.response.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class ObjectResponseDto {
    private String message = "";

    private String code;

    private Boolean success;

    public String getMessage() {
        return message;
    }

    public ObjectResponseDto message(String message) {
        this.message = message;
        return this;
    }

    public ObjectResponseDto success(Boolean success) {
        this.success = success;
        return this;
    }

    public ObjectResponseDto code(String code) {
        this.code = code;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public ResponseEntity<ObjectResponseDto> send(HttpStatus status) {
        return new ResponseEntity<ObjectResponseDto>(this, HttpStatus.OK);
    }
}

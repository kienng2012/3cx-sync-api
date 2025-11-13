package com.java.dto.response.core.impl;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.java.dto.response.core.ObjectResponseDto;

@JsonPropertyOrder({"code", "success", "data", "message"})
public class ErrorResponseDto<T> extends ObjectResponseDto {
    private T data;

    public ErrorResponseDto() {
    }

    public ErrorResponseDto(T data) {
        this();
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public ErrorResponseDto<T> body(T data) {
        this.data = data;
        return this;
    }
}

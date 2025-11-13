package com.java.dto.response.core.impl;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.java.dto.response.core.ObjectResponseDto;

@JsonPropertyOrder({"code", "success", "data", "message"})
public class SuccessResponseDto<T> extends ObjectResponseDto {
    private T data;

    public SuccessResponseDto() {
    }

    public SuccessResponseDto(T data) {
        this();
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public SuccessResponseDto<T> body(T data) {
        this.data = data;
        return this;
    }
}

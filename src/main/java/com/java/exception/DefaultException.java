package com.java.exception;

import com.java.enums.ErrorCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

/**
 * Created by hoangnm on 1/14/20.
 */

@Setter
@Getter
public class DefaultException extends Exception {
    private String errorCode;
    private Optional data;

    public DefaultException(String message) {
        super(message);
    }

    public DefaultException(String message, String errorCode) {
        this(message);
        this.errorCode = errorCode;
        this.data = Optional.empty();
    }

    public DefaultException(ErrorCode error) {
        this(error.getMessage(), error.getCode());
    }

    public DefaultException(ErrorCode error, Optional data) {
        this(error);
        this.data = data;
    }

    public DefaultException(String message, String errorCode, String data) {
        this(message);
        this.errorCode = errorCode;
        this.data = Optional.of(data);
    }
}
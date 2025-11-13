package com.java.dto.response.core.impl;


import com.java.dto.response.core.ObjectResponseDto;
import com.java.enums.ErrorCode;
import com.java.exception.DefaultException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@Log4j2
public class ResponseUtil<T> {
    public static final Integer STT_ACTIVE = 1;
    public static final Integer STT_DELETE = 0;

    public static SuccessResponseDto<Object> success() {
        SuccessResponseDto<Object> objectSuccessResponseDto = new SuccessResponseDto<>();
        objectSuccessResponseDto.success(true);
        objectSuccessResponseDto.message("success");
        return objectSuccessResponseDto;
    }

    public static ErrorResponseDto error() {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.message("error");
        errorResponseDto.success(false);
        return errorResponseDto;
    }

    public static SuccessResponseDto<Object> customStatus() {
        SuccessResponseDto<Object> objectSuccessResponseDto = new SuccessResponseDto<>();
        return objectSuccessResponseDto;
    }

    public static ErrorResponseDto errorWithException(Exception e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.message(e.getMessage());
        errorResponseDto.success(false);
        return errorResponseDto;
    }

    public static ErrorResponseDto errorWithBCNEXException(DefaultException e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.message(e.getMessage());
        errorResponseDto.success(false);
        if (e.getData().isPresent()) {
            errorResponseDto.body(e.getData().get());
        }

        return errorResponseDto;
    }

    public static ResponseEntity<ObjectResponseDto> responseWithException(Exception e) {
        log.error(e.getMessage(), e);
        if (e instanceof DefaultException) {
            return responseWithDefaultException((DefaultException) e);
        }

        return errorWithException(e).code(ErrorCode.UNKNOWN.getCode()).send(HttpStatus.OK);
    }

    public static ResponseEntity<ObjectResponseDto> responseWithDefaultException(DefaultException e) {
        return errorWithBCNEXException(e).code(e.getErrorCode()).send(HttpStatus.OK);
    }

    public static ResponseEntity<ObjectResponseDto> response(Optional optional) {
        if (optional.isPresent()) {
            return success()
                    .body(optional.get())
                    .success(true)
                    .message("success")
                    .code(String.valueOf(HttpStatus.OK.value()))
                    .send(HttpStatus.OK);
        } else {
            return error()
                    .body(null)
                    .message("error")
                    .success(false)
                    .code(ErrorCode.UNKNOWN.getCode())
                    .send(HttpStatus.OK);
        }
    }

    public static ResponseEntity<ObjectResponseDto> responseForUpdate(Optional optional) {
        return success()
                .success(true)
                .message("success")
                .code(String.valueOf(HttpStatus.OK.value()))
                .send(HttpStatus.OK);

    }

    //custom response
    public static ResponseEntity<ObjectResponseDto> responseForCustom(Optional<SuccessResponseDto> dtoOptional) {
        if (dtoOptional.isPresent()) {
            ObjectResponseDto responseDto = dtoOptional.get().code(String.valueOf(HttpStatus.OK.value()));
            return responseDto.send(HttpStatus.OK);
        } else {
            return error()
                    .body(null)
                    .message("error")
                    .success(false)
                    .code(ErrorCode.UNKNOWN.getCode())
                    .send(HttpStatus.OK);
        }
    }
}

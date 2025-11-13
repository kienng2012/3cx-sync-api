package com.java.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VoiceInputDto {
    @NotBlank(message = "DestAddress not blank")
    @NotNull(message = "DestAddress not null")
    private String destAddr;

    public String getDestAddr() {
        return destAddr;
    }
//    @NotBlank(message = "SecretKey không được để trống!")
//    @NotNull(message = "SecretKey không cho phép null!")

    public String getSecretKey() {
        return secretKey;
    }

    private String secretKey;

}

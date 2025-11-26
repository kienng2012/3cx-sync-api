package com.java.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoiceReceiveDto {

    @NotBlank(message = "Username not blank")
    @NotNull(message = "Username not null")
    private String username;
    @NotBlank(message = "DestAddress not blank")
    @NotNull(message = "DestAddress not null")
    private String destAddr;
    private String otp; //Optional
    private String api; //Optional
    private Integer type; //Optional
    private String lan; //Optional
    @NotBlank(message = "SecretKey not blank")
    @NotNull(message = "SecretKey not null")
    private String secretKey;
}

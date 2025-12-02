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
public class VoiceInputDto {
    /*
    @NotBlank(message = "Username not blank")
    @NotNull(message = "Username not null")
    private String username;

     */
    @NotBlank(message = "DestAddress not blank")
    @NotNull(message = "DestAddress not null")
    private String destAddr;
    private String secretKey;

}

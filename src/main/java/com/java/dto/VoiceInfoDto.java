package com.java.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoiceInfoDto {
    private String otp;
    private String api;
    private Integer type;
    private String lan;
    private String method;
}

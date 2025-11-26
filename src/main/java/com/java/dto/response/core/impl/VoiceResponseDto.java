package com.java.dto.response.core.impl;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoiceResponseDto {
    private Long errorCode;
    private String errorMessage;
}

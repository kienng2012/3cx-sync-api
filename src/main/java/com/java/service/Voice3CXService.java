package com.java.service;

import com.java.dto.VoiceInfoDto;

public interface Voice3CXService {
    VoiceInfoDto getVoiceInfo(String destAddr, String secretKey);
}

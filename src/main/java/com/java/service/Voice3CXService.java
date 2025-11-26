package com.java.service;

import com.java.dto.VoiceInfoDto;
import com.java.dto.VoiceInputDto;
import com.java.dto.VoiceReceiveDto;
import com.java.dto.response.core.impl.VoiceResponseDto;

public interface Voice3CXService {
    VoiceInfoDto getVoiceInfoFor3cx(VoiceInputDto dto); //FOR 3cx CAll

    VoiceInfoDto getVoiceInfoForApi(VoiceInputDto dto); //FOR api CAll


    VoiceResponseDto receiveRequestVoice(VoiceReceiveDto dto);
}

package com.java.service.impl;

import com.java.dto.VoiceInfoDto;
import com.java.entity.SubmitOTPQueue;
import com.java.repository.SubmitOTPQueueRepository;
import com.java.service.Voice3CXService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class Voice3CXServiceImpl implements Voice3CXService {

    @Autowired
    private SubmitOTPQueueRepository submitOTPQueueRepository;

    @Value("${api.secretKey}")
    private String secretKeyAuthen;

    @Override
    public VoiceInfoDto getVoiceInfo(String destAddr, String secretKey) {
        if (!secretKeyAuthen.equals(secretKey)) {
            VoiceInfoDto voiceInfoDto = VoiceInfoDto.builder()
                    .type(-2) //Authen failure
                    .build();
            log.info("[getVoiceInfo] Authen failure. Input token={}", secretKey);
            return voiceInfoDto;
        }
        Optional<SubmitOTPQueue> submitOTPQueueOptional = submitOTPQueueRepository.findFirstByDestAddrOrderBySendTimestampDesc(destAddr);
        if (!submitOTPQueueOptional.isPresent()) {
            //1. Khong ton tai OTP hoac het han
            VoiceInfoDto voiceInfoDto = VoiceInfoDto.builder()
                    .type(-1) //Ko ton tai hoac het han
                    .build();
            log.info("[getVoiceInfo] OTP NotFound or Expire OTP");
            return voiceInfoDto;
        }
        SubmitOTPQueue submitOTPQueue = submitOTPQueueOptional.get();
        VoiceInfoDto voiceInfoDto = VoiceInfoDto.builder()
                .type(submitOTPQueue.getTypeService())
                .otp(submitOTPQueue.getShortMessage())
                .api(submitOTPQueue.getApiLookup())
                .lan("vi")
                .build();
        //2.Make status
        submitOTPQueueRepository.moveSubmitOtpToHistoryByID(submitOTPQueue.getId(), submitOTPQueue.getReceiveId());

        return voiceInfoDto;
    }
}

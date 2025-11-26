package com.java.service.impl;

import com.java.dto.VoiceInfoDto;
import com.java.dto.VoiceInputDto;
import com.java.dto.VoiceReceiveDto;
import com.java.dto.response.core.impl.VoiceResponseDto;
import com.java.entity.SubmitOTPQueue;
import com.java.entity.Users;
import com.java.repository.ReceiveRequestVoiceRepositoryCustom;
import com.java.repository.SubmitOTPQueueRepository;
import com.java.schedule.ReloadUser;
import com.java.service.Voice3CXService;
import com.java.util.CommonUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class Voice3CXServiceImpl implements Voice3CXService {

    private static final Integer STATUS_PENDING = 0;

    @Autowired
    private SubmitOTPQueueRepository submitOTPQueueRepository;

    @Value("${api.secretKey}")
    private String secretKeyAuthen;
    @Autowired
    private ReceiveRequestVoiceRepositoryCustom receiveRequestVoiceRepository;


    /**
     * API for 3cx call due 3cx not process concatenate string & encrypt md5. => Using bear token authenticate
     *
     * @param dto
     * @return
     */
    @Override
    public VoiceInfoDto getVoiceInfoFor3cx(VoiceInputDto dto) {
        if (!secretKeyAuthen.equals(dto.getSecretKey())) {
            VoiceInfoDto voiceInfoDto = VoiceInfoDto.builder()
                    .type(-2) //Authen failure
                    .build();
            log.info("[getVoiceInfoForApi] USER_NOT_ALLOWED {}", dto);
            return voiceInfoDto;
        }
        //AUTHENTICATE SUCCESS => PROCESS BUSINESS
        return this.processGetVoiceInfo(dto.getUsername(), dto.getDestAddr());
    }

    /**
     * FOR API: process OTP
     *
     * @param dto systemSecretkey = MD5(username+destAddr+privateKey);
     * @return
     */
    @Override
    public VoiceInfoDto getVoiceInfoForApi(VoiceInputDto dto) {
        log.info("[getVoiceInfoForApi] VoiceInputDto={}", dto);
        VoiceInfoDto voiceInfoDto;
        if (!this.validateAuthenticateApi(dto.getUsername(), dto.getDestAddr(), dto.getSecretKey())) {
            voiceInfoDto = VoiceInfoDto.builder()
                    .type(-2) //Authen failure
                    .build();
            log.info("[getVoiceInfoForApi] USER_NOT_ALLOWED {}", dto);
            return voiceInfoDto;
        }
        //AUTHENTICATE SUCCESS => PROCESS BUSINESS
        return this.processGetVoiceInfo(dto.getUsername(), dto.getDestAddr());
    }

    /**
     * FOR API request receive OTP
     *
     * @param dto systemSecretkey = MD5(username+destAddr+privateKey);
     * @return
     */
    @Override
    public VoiceResponseDto receiveRequestVoice(VoiceReceiveDto dto) {
        log.info("[receiveRequestVoice] VoiceReceiveDto={}", dto);
        VoiceResponseDto voiceResponseDto;
        if (!this.validateAuthenticateApi(dto.getUsername(), dto.getDestAddr(), dto.getSecretKey())) {
            voiceResponseDto = VoiceResponseDto.builder()
                    .errorCode(-2L)
                    .errorMessage("USER_NOT_ALLOWED !")
                    .build();
            return voiceResponseDto;
        }
        //VALID AUTHENTICATE => PROCESS BUSINESS
        return this.processReceiveRequestVoice(dto);
    }

    private VoiceInfoDto processGetVoiceInfo(String username, String destAddr) {
        Optional<SubmitOTPQueue> submitOTPQueueOptional = submitOTPQueueRepository.findFirstByUsernameAndDestAddrAndStatusOrderBySendTimestampDesc(username, destAddr, STATUS_PENDING);
        if (!submitOTPQueueOptional.isPresent()) {
            //1. Khong ton tai OTP hoac het han
            VoiceInfoDto voiceInfoDto = VoiceInfoDto.builder()
                    .type(-1) //Ko ton tai hoac het han
                    .build();
            log.info("[processGetVoiceInfo] OTP NotFound or Expire OTP. Dest Addr={}", destAddr);
            return voiceInfoDto;
        }
        SubmitOTPQueue submitOTPQueue = submitOTPQueueOptional.get();
        VoiceInfoDto voiceInfoDto = VoiceInfoDto.builder()
                .type(submitOTPQueue.getTypeService())
                .otp(submitOTPQueue.getShortMessage())
                .api(submitOTPQueue.getApiLookup())
                .lan(CommonUtil.isNullOrEmpty(submitOTPQueue.getLanguage()) ? "vi" : submitOTPQueue.getLanguage())
                .method(submitOTPQueue.getMethod())
                .build();
        log.info("[processGetVoiceInfo] OTP Found Dest Addr={}, VoiceInfoDto={}", destAddr, voiceInfoDto);
        //2.Make status
        submitOTPQueueRepository.moveSubmitOtpToHistoryByID(submitOTPQueue.getId(), submitOTPQueue.getReceiveId());

        return voiceInfoDto;
    }

    private VoiceResponseDto processReceiveRequestVoice(VoiceReceiveDto dto) {
        //VALID AUTHENTICATE => PROCESS BUSINESS
        Long resultInsert = receiveRequestVoiceRepository.insertRequestVoice(dto.getUsername(), dto.getDestAddr(), dto.getOtp(), dto.getApi(), dto.getType(), dto.getLan());
        VoiceResponseDto voiceResponseDto = new VoiceResponseDto();
        if (resultInsert != null && resultInsert > 0) {
            voiceResponseDto.setErrorCode(resultInsert);
            voiceResponseDto.setErrorMessage("SUCCESS");
        } else {
            voiceResponseDto.setErrorCode(-99L);
            voiceResponseDto.setErrorMessage("FAIL");
        }
        return voiceResponseDto;
    }

    private Boolean validateAuthenticateApi(String username, String destAddr, String secretKey) {
        if (ReloadUser.mapUsers.isEmpty() || !ReloadUser.mapUsers.containsKey(username)) {
            log.info("[validateAuthenticateApi] USER_NOT_CACHE. Username= {}, destAddr= {}, secretKey= {}", username, destAddr, secretKey);
            return false;
        }

        Users users = ReloadUser.mapUsers.get(username);
        if (users.getSharekey() == null || users.getSharekey().isEmpty()) {
            log.info("[validateAuthenticateApi] USER_NOT_CONFIG_PRIVATEKEY. Username= {}, destAddr= {}, secretKey= {}", username, destAddr, secretKey);
            return false;
        }
        String encryptKey = CommonUtil.encryptMd5(username + destAddr + users.getSharekey());
        if (!encryptKey.equalsIgnoreCase(secretKey)) {
            log.info("[validateAuthenticateApi] SECRET_KEY_NOT_VALID. Username= {}, destAddr= {}, secretKey= {}. expectSecretKey={}", username, destAddr, secretKey, encryptKey);
            return false;
        }
        return true;
    }
}

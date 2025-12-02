package com.java.controller;

import com.java.dto.VoiceInputDto;
import com.java.dto.VoiceReceiveDto;
import com.java.service.Voice3CXService;
import com.java.util.CommonUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Log4j2
@RequestMapping("/sync-3cx-api/")
public class QuayThuongVTrueController {
    @Autowired
    private Voice3CXService voice3CXService;

    /*

        @PostMapping("/voice/answer")
        @Transactional(rollbackFor = Exception.class)
        public ResponseEntity<?> luckyCodeActionForAPI(@Valid() @RequestBody LuckyCodeDto dto) {
            log.info(dto);
            try {//TODO SecretKey validate : MD5
                String tmpSecretKey = CommonUtil.encryptMd5(privateKey + dto.getTypeAction() + dto.getMaTem() + dto.getSoDienThoai() + dto.getMaSP() + dto.getMaDN());
                log.info("[luckyCodeActionForAPI]-tmpSecretKey= {}", tmpSecretKey);
                if (dto.getSecretKey() != null && tmpSecretKey.equals(dto.getSecretKey())) {
                    luckyCodePendingRepository.luckyCodeActionForAPI(dto.getTypeAction(), dto.getLevel(), dto.getSoDienThoai(), dto.getMaTem(),
                            dto.getMaSP(), dto.getTenSP(), dto.getMaDN(), dto.getTenDN(), dto.getNgayXacThuc(), dto.getKyQuayThuong());
                    log.info("[luckyCodeAction] SUCCESS. Detail {}", dto);
                    return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
                } else {
                    log.info("[luckyCodeAction] AUTHEN FAILED. Detail {}", dto);
                    return new ResponseEntity<>(Boolean.FALSE, HttpStatus.UNAUTHORIZED);
                }
            } catch (Exception ex) {
                log.error("[luckyCodeAction] Exception: {}", ex);
                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
            }

        }
        */

    /**
     * For API call to receive request OTP
     *
     * @param dto
     * @return
     */
    @PostMapping("/voice/receiveRequestOtp")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> receiveRequestOtp(@Valid() @RequestBody VoiceReceiveDto dto) {
        log.info(dto);
        try {//TODO SecretKey validate : MD5
            return new ResponseEntity<>(voice3CXService.receiveRequestVoice(dto), HttpStatus.OK);
        } catch (Exception ex) {
            log.error("[receiveRequestOtp] Exception: {}", ex);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * For API call to process Get OTP
     *
     * @param dto
     * @return
     */

    /*
    @PostMapping("/voice/processOtp")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> processOtp(@Valid() @RequestBody VoiceInputDto dto) {
        log.info(dto);
        try {
            return new ResponseEntity<>(voice3CXService.getVoiceInfoForApi(dto), HttpStatus.OK);
        } catch (Exception ex) {
            log.error("[processOtp] Exception: {}", ex);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    */

    /**
     * For 3cx call to process Get OTP
     *
     * @param authHeader
     * @param dto
     * @return type=-2:Authen | -1:Ko co OTP| 0: Pass Khong can doc | 1: Doc OTP
     */
    @PostMapping("/voice/processOtp3cx")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> processOtp3cx(@RequestHeader("Authorization") String authHeader, @Valid() @RequestBody VoiceInputDto dto) {
        if (!CommonUtil.isNullOrEmpty(authHeader)) dto.setSecretKey(authHeader);
        log.info(dto);
        try {//TODO SecretKey validate : MD5
            return new ResponseEntity<>(voice3CXService.getVoiceInfoFor3cx(dto), HttpStatus.OK);
        } catch (Exception ex) {
            log.error("[processOtp3cx] Exception: {}", ex);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

/*
    @GetMapping("/vtrue/luckyStampQueue/{stampCode}")
    public ResponseEntity<?> findStampCode(@RequestHeader("secretKey") String secretKey, @PathVariable String stampCode
    ) {
        try {
            String tmpSecretKey = CommonUtil.encryptMd5(privateKey + stampCode);
            log.info("[luckyCodeActionForAPI]-tmpSecretKey= {}", tmpSecretKey);
            if (secretKey != null && tmpSecretKey.equals(secretKey)) {
                Optional<LuckyCodeQueue> optionalQuayThuongQueue = quayThuongQueueRepository.findByMaTem(stampCode);
                if (optionalQuayThuongQueue.isPresent()) {
                    return new ResponseEntity<>(optionalQuayThuongQueue.get(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new LuckyCodeQueue(), HttpStatus.NOT_FOUND);
                }
            } else {
                log.info("[luckyCodeAction] AUTHEN FAILED. stampCode = {}", stampCode);
                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception e) {
            log.error("[findStampCode] Exception: {}", e);
            return new ResponseEntity<>(new LuckyCodeQueue(), HttpStatus.BAD_GATEWAY);
        }
    }

 */

    /**
     * * @param typeAction @typeAction=1: Giai nhat | 2: Giai nhi | 3: giai 3 | 4: giai khuyen khich
     *
     * @param req
     * @param typeAction
     * @param typeLoad
     * @return
     */
    /*
    @RequestMapping("/getRandomLucky")
    public ResponseEntity<LuckyCodeQueue> getRandowmLucky(
            HttpServletRequest req,
            @RequestParam(name = "typeAction", defaultValue = "1", required = true) Integer typeAction,
            @RequestParam(name = "typeLoad", required = false) String typeLoad
    ) {
        try {
            Optional<LuckyCodeQueue> optionalQuayThuongQueue = quayThuongQueueRepository.getRandomLucky(1);



//            return new ResponseEntity<>("{\"status\" : \"UP\"}", HttpStatus.OK);
            if (optionalQuayThuongQueue.isPresent()) {
                return new ResponseEntity<>(optionalQuayThuongQueue.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new LuckyCodeQueue(), HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<>(new LuckyCodeQueue(), HttpStatus.BAD_GATEWAY);
        }
    }


    @PostMapping("/makeLuckyByStampCode")
//    @Transactional(rollbackFor = Exception.class)
//    @Modifying
    public ResponseEntity<Boolean> makeUpdateUser(@RequestBody StampDto dto) {
        String msg;
        try {
            quayThuongQueueRepository.makeStampLucky(dto.getMaTem());
        } catch (Exception ex) {
            log.error("[makeUpdateUser] Exception: {}", ex);
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
//            msg = "Có lỗi trong quá trình xử lý: " + ex.getMessage();
        }
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }
*/

}

package com.java.controller;

import com.java.dto.VoiceInputDto;
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
    @PostMapping("/voice/answer")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> answer(
            @RequestHeader("Authorization") String authHeader,
            @Valid() @RequestBody VoiceInputDto dto) {
        log.info(dto);
        try {//TODO SecretKey validate : MD5
            return new ResponseEntity<>(voice3CXService.getVoiceInfo(dto.getDestAddr(), authHeader), HttpStatus.OK);
            /*
            if (dto.getSecretKey() != null && tmpSecretKey.equals(dto.getSecretKey())) {

                return new ResponseEntity<>(voice3CXService.getVoiceInfo(dto.getDestAddr(), dto.getSecretKey()), HttpStatus.OK);
            } else {
                log.info("[answer] AUTHEN FAILED. Detail {}", dto);
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }

             */
        } catch (Exception ex) {
            log.error("[answer] Exception: {}", ex);
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

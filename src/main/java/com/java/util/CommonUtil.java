package com.java.util;

import lombok.extern.log4j.Log4j2;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Log4j2
public class CommonUtil {

    public static String encryptMd5(String str) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            return myHash;
        } catch (NoSuchAlgorithmException e) {
            log.error("[encryptMd5] Error=" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    public static boolean isNullOrEmpty(String str) {
        return (str == null || str.isEmpty());
    }
}

//package com.java.util;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.exceptions.JWTDecodeException;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.google.common.base.Strings;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Optional;
//
///**
// * Created by hoangnm on 1/14/20.
// */
//public class JwtUtil {
//    public static Optional<String> getUsernameFromRequest(HttpServletRequest request) {
//        String authHeader = request.getHeader("Authorization");
//        if (Strings.isNullOrEmpty(authHeader)) return Optional.empty();
//
//        String token = authHeader.substring(7); // remove "Bearer "
//
//        try {
//            DecodedJWT jwt = JWT.decode(token);
//            return Optional.ofNullable(jwt.getClaim("user_name").asString());
//
//        } catch (JWTDecodeException exception){
//            return Optional.empty();
//        }
//    }
//}
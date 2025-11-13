package com.java.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    public static final String REGEX_EMAIL = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(REGEX_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validateIsNumber(String someString) {
        return someString.chars().allMatch(Character::isDigit);
    }

    public static boolean validateIsPhone(String phone) {
        if (phone.isEmpty())
            return false;
        phone = phone.trim();
        if (phone.length() < 10 || phone.length() > 11)
            return false;
        if (phone.startsWith("0") || phone.startsWith("84"))
            return validateIsNumber(phone);
        else
            return false;

    }

}

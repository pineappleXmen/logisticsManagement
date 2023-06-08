package com.pineapple.pineapplelogistics.utils;

import java.util.regex.Pattern;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 20:03 2023/4/23
 * @Modifier by:
 */
public class PhoneUtils {
    public static boolean isValid(String phone){
        String regex = "^(13[0-9]|14[5-9]|15[0-3,5-9]|16[5-6]|17[0-8]|18[0-9]|19[1,8,9])\\d{8}$";
        return Pattern.matches(regex, phone);
    }
}

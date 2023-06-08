package com.pineapple.pineapplelogistics.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.validator.constraints.Length;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 14:33 2023/2/10
 * @Modifier by:
 */
public class DigestUtil {
    public static String encodeMd5Hex(String code){
        return DigestUtils.md5Hex(code);
    }

    public static String encodeSha1Hex(String code){
        return DigestUtils.sha1Hex(code);
    }

    public static String encodePassword(String password,@Length(min = 32,max = 32) String salt){
        String code = salt.substring(16,20)+password+salt.substring(0, 16)+ salt.substring(20,32);
        return DigestUtil.encodeSha1Hex(DigestUtil.encodeMd5Hex(code));
    }
}

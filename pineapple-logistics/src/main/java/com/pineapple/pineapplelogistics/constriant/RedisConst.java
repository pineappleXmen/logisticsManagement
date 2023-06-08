package com.pineapple.pineapplelogistics.constriant;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 19:08 2023/4/24
 * @Modifier by:
 */
public class RedisConst {
    public static final String LOGIN_KEY_PREFIX = "login:key:";
    public static final String LOGIN_USERNAME_KEY_PREFIX = "login:username:key:";
    public static final String LOGIN_SALT_PREFIX = "login:salt:";
    public static final String LOGIN_TOKEN_PREFIX = "login:token:";
    public static final String EMAIL_CODE_PREFIX = "email:code:";

    public static final String PASSWORD_CODE_PREFIX = "password:code:";

    public static final String USER_VO_SUFFIX =":uservo";
    public static final Long LOGIN_TOKEN_TTL = 30L;
    public static final Long LOGIN_REMEMBER_TTL = 7L;

    public static final Long FREQUENTLY_REQUEST_FOR_CODE_TTL = 120L;
}

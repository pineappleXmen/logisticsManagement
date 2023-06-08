package com.pineapple.pineapplelogistics.utils;

import java.util.UUID;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 3:54 2023/4/23
 * @Modifier by:
 */
public class UUIDUtils {
    /**
     *
     * @return 返回32位UUID
     */
    public static String get(){
        return UUID.randomUUID().toString().replace("-","");
    }
}

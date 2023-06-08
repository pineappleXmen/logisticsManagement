package com.pineapple.pineapplelogistics.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * @Author:Pineapple
 * @Description:
 * @Date: 21:44 2023/3/14
 * @Modifier by:
 */
public class DateUtils {


    public static long getCurrentTimeStamp(){
        return System.currentTimeMillis();
    }


    public static LocalDateTime now(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = LocalDateTime.now().format(dtf);
        LocalDateTime res = LocalDateTime.parse(format,dtf);
        return res;
    }

}

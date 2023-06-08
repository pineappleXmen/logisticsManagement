package com.pineapple.pineapplelogistics.exception;

import com.pineapple.pineapplelogistics.bean.ResultEnum;
import lombok.Data;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 19:12 2023/4/23
 * @Modifier by:
 */
@Data
public class SnowflakeException extends RuntimeException{
    private ResultEnum resultEnum;
    public SnowflakeException(String msg){
        super(msg);
    }
}

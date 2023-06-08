package com.pineapple.pineapplelogistics.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 20:14 2023/4/22
 * @Modifier by:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultBean {
    private long code;
    private String message;
    private Object data;
    public static ResultBean success(){
        return new ResultBean(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage(), null);
    }
    public static ResultBean success(Object obj){
        return new ResultBean(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage(), obj);
    }
    public static ResultBean success(ResultEnum resultEnum,Object obj){
        return new ResultBean(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage(), obj);
    }
    public static ResultBean warn(ResultEnum resultEnum,Object data){
        return new ResultBean(resultEnum.getCode(), resultEnum.getMessage(),data);
    }
    public static ResultBean error(ResultEnum resultEnum){
        return new ResultBean(resultEnum.getCode(), resultEnum.getMessage(), null);
    }
    public static ResultBean error(ResultEnum resultEnum,Object obj){
        return new ResultBean(resultEnum.getCode(), resultEnum.getMessage(), obj);
    }
}

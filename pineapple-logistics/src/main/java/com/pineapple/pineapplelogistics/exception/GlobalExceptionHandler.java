package com.pineapple.pineapplelogistics.exception;

import com.pineapple.pineapplelogistics.bean.ResultBean;
import com.pineapple.pineapplelogistics.bean.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 19:10 2023/4/23
 * @Modifier by:
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResultBean exceptionHandler(Exception e){
        if(e instanceof GlobalException){
            GlobalException ex = (GlobalException) e;
            return ResultBean.error(ex.getResultEnum());
        }else if(e instanceof BindException){
            BindException bx = (BindException) e;
            ResultBean error = ResultBean.error(ResultEnum.BINDING_ERROR);
            error.setMessage("[Validation Error] "+bx.getBindingResult().getAllErrors().get(0).getDefaultMessage());
            return error;
        }else {
            log.debug("[Error] {}",e.getMessage());
            return ResultBean.error(ResultEnum.ERROR,e.getMessage());
        }
    }
    
    
    @ExceptionHandler(SnowflakeException.class)
    public ResultBean snowflakeException(SnowflakeException ex){
        ResultBean error = ResultBean.error(ResultEnum.SNOWFLAKE_ERROR);
        log.warn("[snowflakeError] msg:{}",ex.getMessage());
        error.setMessage(ex.getMessage());
        return error;
    }
}

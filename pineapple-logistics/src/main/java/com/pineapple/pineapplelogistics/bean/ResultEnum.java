package com.pineapple.pineapplelogistics.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 20:15 2023/4/22
 * @Modifier by:
 */

@Getter
@ToString
@AllArgsConstructor
public enum ResultEnum {
    //请求成功
    SUCCESS(200,"Success"),
    CODE_SEND_SUCCESS(200,"Validation Code Already send to email box"),
    SIGN_UP_SUCCESS(200,"SignUp Success"),
    PAYMENT_CREATED(200,"Success create new payment"),
    PAYMENT_UPDATED(200,"Success update payment"),
    GOODS_CREATED(200,"Success create new goods"),
    GOODS_UPDATED(200,"Success update goods"),
    ORDER_CREATED(200,"Success create order"),
    ORDER_UPDATED(200,"Success update order"),

    //请求失败
    ERROR(500,"Server Error"),

    //5001 表示登录过程的错误
    USER_NOT_EXIST(500110,"User Do Not Exist"),
    LOGIN_ERROR(500111,"Username Or Phone Number Error"),
    PHONE_FORMAT_ERROR(500112,"Wrong Phone Number Format Error"),
    WRONG_PASSWORD(500113,"Password Is Wrong"),
    BINDING_ERROR(500114,"Binding Error"),
    PERMISSION_DENIED(500115,"Permission denied"),
    CODE_EXPIRED(500116,"Validation code is expired,please try again"),
    CODE_ERROR(500117,"Validation code is wrong"),
    FREQUENTLY_REQUEST_FOR_CODE(500118,"Too much request in one minute,please wait and retry"),
    SIGN_UP_ERROR(500117,"SignUp error"),
    USER_ALREADY_EXIST(500118,"Your email has already signed up"),
    CHANGE_PASSWORD_ERROR(500119,"Something wrong when change password"),

    //5002表示登出时的错误
    USER_NOT_LOGIN(500210,"User do not login"),

    //5003表示调用服务时发生的错误
    PAYMENT_SERVICE_ERROR(500310,"Payment service error"),
    GOODS_SERVICE_ERROR(500311,"Goods service error"),
    GOODS_VO_NULL_ERROR(500312,"GoodsVo is null"),
    PAYMENT_VO_NULL_ERROR(500312,"PaymentVo is null"),
    ORDER_SERVICE_ERROR(500313,"Order Service is error"),

    //5004表示调用雪花算法生成ID时遇到的错误
    SNOWFLAKE_ERROR(500410,"SnowFlake get error report"),

    //5005 表示获取验证码时的错误
    VALIDATOR_CODE_SEND_ERROR(500510,"Validator code send error");
    private final Integer code;
    private final String message;
}

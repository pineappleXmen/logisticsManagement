package com.pineapple.pineapplelogistics.controller;

import com.pineapple.pineapplelogistics.bean.ResultBean;
import com.pineapple.pineapplelogistics.service.IAuthorizeService;
import com.pineapple.pineapplelogistics.service.IUserService;
import com.pineapple.pineapplelogistics.vo.SignUpVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.pineapple.pineapplelogistics.constriant.RedisConst.EMAIL_CODE_PREFIX;
import static com.pineapple.pineapplelogistics.constriant.RedisConst.PASSWORD_CODE_PREFIX;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 21:02 2023/5/3
 * @Modifier by:
 */
@RestController
@Validated
@RequestMapping("/api/auth")
public class AuthorizeController {

    @Autowired
    private IAuthorizeService authorizeService;
    @Autowired
    private IUserService userService;


    @PostMapping("/valid-register-email")
    public ResultBean validateRegisterEmail(@Email @RequestParam("email") String email, HttpSession session){
        return authorizeService.sendValidateEmailCode(email,session,EMAIL_CODE_PREFIX);
    }

    @PostMapping("/valid-register-change-password")
    public ResultBean validateChangePasswordEmail(@Email @RequestParam("email") String email, HttpSession session){
        return authorizeService.sendValidateEmailCode(email,session,PASSWORD_CODE_PREFIX);
    }

    @PostMapping("/register")
    @ResponseBody
    public ResultBean doSignUp(SignUpVo signUpVo, HttpServletRequest request, HttpServletResponse response,HttpSession session){
        return userService.doSignUp(signUpVo, request, response, session);
    }


    @PostMapping("/change_password")
    @ResponseBody
    public ResultBean doChangePassword(@RequestParam @Email @NotNull String email, @RequestParam @NotNull @Length(min = 6,max = 6) String code, HttpSession session){
        return userService.doChangePassword(email,code,session);
    }

    @PostMapping("/do_reset")
    @ResponseBody
    public ResultBean doResetPassword(@RequestParam @NotNull @Length(min = 6) String password, HttpSession session){
        return userService.doReset(password,session);
    }


}

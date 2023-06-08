package com.pineapple.pineapplelogistics.service;

import com.pineapple.pineapplelogistics.bean.ResultBean;
import com.pineapple.pineapplelogistics.vo.LoginVo;
import com.pineapple.pineapplelogistics.vo.SignUpVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 0:27 2023/4/25
 * @Modifier by:
 */
public interface IUserService {
    ResultBean doSignUp(SignUpVo signUpVo, HttpServletRequest request, HttpServletResponse response, HttpSession session);
    ResultBean doChangePassword(String email,String code,HttpSession session);
    ResultBean doReset(String password,HttpSession session);
}

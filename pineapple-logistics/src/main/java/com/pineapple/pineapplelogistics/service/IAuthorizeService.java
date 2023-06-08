package com.pineapple.pineapplelogistics.service;

import com.pineapple.pineapplelogistics.bean.ResultBean;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 20:57 2023/5/3
 * @Modifier by:
 */
public interface IAuthorizeService extends UserDetailsService {
    ResultBean sendValidateEmailCode(String email, HttpSession httpSession, String prefix);
}

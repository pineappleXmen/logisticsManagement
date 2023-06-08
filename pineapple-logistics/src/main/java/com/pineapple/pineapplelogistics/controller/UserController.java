package com.pineapple.pineapplelogistics.controller;

import com.pineapple.pineapplelogistics.bean.ResultBean;
import com.pineapple.pineapplelogistics.bean.ResultEnum;
import com.pineapple.pineapplelogistics.entity.User;
import com.pineapple.pineapplelogistics.utils.ThreadlocalUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pineapple.pineapplelogistics.constriant.ThreadlocalConst.THREAD_LOCAL_USER_KEY;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 17:07 2023/5/4
 * @Modifier by:
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @GetMapping("/me")
    public ResultBean me(){
        User user = (User) ThreadlocalUtils.get(THREAD_LOCAL_USER_KEY);
        System.out.println(user);
        return ResultBean.success(user);
    }
}

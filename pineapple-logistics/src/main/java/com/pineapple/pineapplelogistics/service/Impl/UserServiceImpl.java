package com.pineapple.pineapplelogistics.service.Impl;

import com.pineapple.pineapplelogistics.bean.ResultBean;
import com.pineapple.pineapplelogistics.bean.ResultEnum;
import com.pineapple.pineapplelogistics.entity.User;
import com.pineapple.pineapplelogistics.mapper.UserMapper;
import com.pineapple.pineapplelogistics.service.IUserService;
import com.pineapple.pineapplelogistics.utils.DateUtils;
import com.pineapple.pineapplelogistics.vo.LoginVo;
import com.pineapple.pineapplelogistics.vo.SignUpVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.pineapple.pineapplelogistics.constriant.RedisConst.*;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 11:14 2023/5/4
 * @Modifier by:
 */
@Service
public class UserServiceImpl implements IUserService {


    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Resource
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultBean doSignUp(SignUpVo signUpVo, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String key = EMAIL_CODE_PREFIX+session.getId()+signUpVo.getEmail();
        Boolean hasKey = redisTemplate.hasKey(key);
        if (Boolean.TRUE.equals(hasKey)){
            String s = (String) redisTemplate.opsForValue().get(key);
            if (s==null){
                return ResultBean.warn(ResultEnum.CODE_EXPIRED,null);
            }
            if (s.equals(signUpVo.getCode())){
                User userById = userMapper.findUserById(signUpVo.getEmail());
                if (userById!=null){
                    return ResultBean.error(ResultEnum.USER_ALREADY_EXIST);
                }
                String encode = encoder.encode(signUpVo.getPassword());
                User user = new User();
                user.setNickname(signUpVo.getName());
                user.setSalt("");
                user.setAvatar("");
                user.setPassword(encode);
                user.setEmail(signUpVo.getEmail());
                user.setPhone(signUpVo.getPhone());
                user.setRegistry_time(DateUtils.now());
                user.setRole(1);
                user.setLogin_count(0L);
                int msg = userMapper.createNewUser(user);
                if (msg>=1){
                    return ResultBean.success(ResultEnum.SIGN_UP_SUCCESS);
                }else {
                    return ResultBean.error(ResultEnum.SIGN_UP_ERROR);
                }
            }else {
                return ResultBean.error(ResultEnum.CODE_ERROR);
            }
        }else {
            return ResultBean.warn(ResultEnum.CODE_EXPIRED,null);
        }
    }


    @Override
    public ResultBean doChangePassword(String email,String code,HttpSession session) {
        String key = PASSWORD_CODE_PREFIX + session.getId() + email;
        Boolean hasKey = redisTemplate.hasKey(key);
        if (Boolean.TRUE.equals(hasKey)) {
            String s = (String) redisTemplate.opsForValue().get(key);
            if (s == null) {
                return ResultBean.warn(ResultEnum.CODE_EXPIRED, null);
            }
            if (s.equals(code)) {
                session.setAttribute("reset_pass",email);
                return ResultBean.success();
            } else {
                return ResultBean.error(ResultEnum.CODE_ERROR);
            }
        } else {
            return ResultBean.error(ResultEnum.CHANGE_PASSWORD_ERROR);
        }
    }

    @Override
    public ResultBean doReset(String password, HttpSession session) {
        String email = (String) session.getAttribute("reset_pass");
        if (email==null){
            return ResultBean.error(ResultEnum.CHANGE_PASSWORD_ERROR);
        }
        String encode = encoder.encode(password);
        int i = userMapper.updatePassword(encode, email);
        if (i>=1){
            return ResultBean.success();
        }else {
            return ResultBean.error(ResultEnum.CHANGE_PASSWORD_ERROR);
        }
    }
}

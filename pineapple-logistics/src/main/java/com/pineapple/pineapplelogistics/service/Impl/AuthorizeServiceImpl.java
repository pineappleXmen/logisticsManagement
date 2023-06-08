package com.pineapple.pineapplelogistics.service.Impl;

import com.pineapple.pineapplelogistics.bean.ResultBean;
import com.pineapple.pineapplelogistics.bean.ResultEnum;
import com.pineapple.pineapplelogistics.entity.User;
import com.pineapple.pineapplelogistics.mapper.UserMapper;
import com.pineapple.pineapplelogistics.service.IAuthorizeService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.pineapple.pineapplelogistics.constriant.RedisConst.EMAIL_CODE_PREFIX;
import static com.pineapple.pineapplelogistics.constriant.RedisConst.FREQUENTLY_REQUEST_FOR_CODE_TTL;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 16:07 2023/5/3
 * @Modifier by:
 */
@Service
public class AuthorizeServiceImpl implements IAuthorizeService {

    @Resource
    UserMapper userMapper;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Resource
    private MailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)){
            throw new UsernameNotFoundException("Username is null");
        }
        User user = userMapper.findUserById(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getNickname())
                .password(user.getPassword())
                .roles("user").build();
    }

    @Override
    public ResultBean sendValidateEmailCode(String email, HttpSession session,String prefix) {
        String key = prefix + session.getId() + email;
        Long expire = Optional.ofNullable(redisTemplate.getExpire(key, TimeUnit.SECONDS)).orElse(0L);
        if (expire>FREQUENTLY_REQUEST_FOR_CODE_TTL){
            return ResultBean.error(ResultEnum.FREQUENTLY_REQUEST_FOR_CODE);
        }

        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom(from);
        smm.setTo(email);
        smm.setSubject("[注册邮箱验证]您的注册验证码");
        smm.setText("您的验证码是:"+code+" 验证码3分钟内有效，请您尽快完成注册！");
        try{
            mailSender.send(smm);

            redisTemplate.opsForValue().set(key,String.valueOf(code),3, TimeUnit.MINUTES);
            return ResultBean.success();
        }catch (MailException e){
            e.printStackTrace();
            return ResultBean.error(ResultEnum.ERROR,e);
        }
    }
}

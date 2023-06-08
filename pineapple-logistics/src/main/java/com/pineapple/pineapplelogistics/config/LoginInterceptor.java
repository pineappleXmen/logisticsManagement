package com.pineapple.pineapplelogistics.config;


import com.pineapple.pineapplelogistics.entity.User;
import com.pineapple.pineapplelogistics.mapper.UserMapper;
import com.pineapple.pineapplelogistics.utils.DateUtils;
import com.pineapple.pineapplelogistics.utils.ThreadlocalUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import static com.pineapple.pineapplelogistics.constriant.ThreadlocalConst.THREAD_LOCAL_USER_KEY;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 18:54 2023/4/24
 * @Modifier by:
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private final RedisTemplate<String,Object> redisTemplate;

    @Resource
    private final UserMapper userMapper;

    public LoginInterceptor(RedisTemplate<String,Object> redisTemplate,UserMapper userMapper){
        this.redisTemplate = redisTemplate;
        this.userMapper = userMapper;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        org.springframework.security.core.userdetails.User user1 = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        String username = user1.getUsername();
        User userInfo = userMapper.findUserByName(username);
        userInfo.setId(0L);
        userInfo.setPassword("");
        userInfo.setLast_login_time(DateUtils.now());
        ThreadlocalUtils.put(THREAD_LOCAL_USER_KEY,userInfo);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadlocalUtils.removeKey(THREAD_LOCAL_USER_KEY);
    }
}


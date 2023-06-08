package com.pineapple.pineapplelogistics.config;

import com.pineapple.pineapplelogistics.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 0:24 2023/4/25
 * @Modifier by:
 */
@Configuration
public class MVCConfig implements WebMvcConfigurer {

    @Autowired
    RedisTemplate<String,Object> objectRedisTemplate;

    @Autowired
    UserMapper userMapper;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor(objectRedisTemplate,userMapper))
                .addPathPatterns("/**")
                .excludePathPatterns("/api/auth/**");
        registry.addInterceptor(new ResponseInterceptor());
    }
}

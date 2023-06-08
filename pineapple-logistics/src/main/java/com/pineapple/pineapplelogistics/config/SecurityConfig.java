package com.pineapple.pineapplelogistics.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pineapple.pineapplelogistics.bean.ResultBean;
import com.pineapple.pineapplelogistics.bean.ResultEnum;
import com.pineapple.pineapplelogistics.mapper.UserMapper;
import com.pineapple.pineapplelogistics.service.IAuthorizeService;
import com.pineapple.pineapplelogistics.utils.DateUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import java.io.IOException;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 15:43 2023/5/3
 * @Modifier by:
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    private IAuthorizeService authorizeService;

    @Resource
    private RedisTokenRepo redisTokenRepo;

    @Resource
    private UserMapper userMapper;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/api/auth/login")
                .successHandler(this::onAuthenticationSuccess)
                .failureHandler(this::onAuthenticationFailure)
                .and()
                .logout()
                .logoutUrl("/api/auth/logout")
                .logoutSuccessHandler(this::onAuthenticationSuccess)
                .and()
                .rememberMe()
                .rememberMeParameter("remember")
                .tokenRepository(redisTokenRepo)
                .rememberMeCookieName("login")
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(this::commence)
                .and()
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity security) throws Exception {
        return security
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(authorizeService)
                .and()
                .build();
    }


    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setCharacterEncoding("utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        if (request.getRequestURL().toString().endsWith("/login")) {
            SecurityContext context = SecurityContextHolder.getContext();
            User user = (User) context.getAuthentication().getPrincipal();
            int i = userMapper.updateLastLoginTime(DateUtils.now(), user.getUsername());
            int j = userMapper.updateLoginCount(user.getUsername());
            if (i >= 1 && j>=1) {
                response.getWriter().write(objectMapper.writeValueAsString(ResultBean.success(ResultEnum.SUCCESS)));
            }
        }else {
                response.getWriter().write(objectMapper.writeValueAsString(ResultBean.success("退出登录成功")));
        }
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(ResultBean.error(ResultEnum.LOGIN_ERROR)));
    }
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(ResultBean.error(ResultEnum.PERMISSION_DENIED)));
    }



}

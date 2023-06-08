package com.pineapple.pineapplelogistics.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 3:20 2023/5/7
 * @Modifier by:
 */
public class ResponseInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            Object data = modelAndView.getModel().get("data");
            if (data instanceof LocalDateTime) {
                LocalDateTime localDateTime = (LocalDateTime) data;
                long timestamp = localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
                modelAndView.getModel().put("data", timestamp);
            }
        }
    }
}

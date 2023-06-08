package com.pineapple.pineapplelogistics.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.Map;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 21:14 2023/4/24
 * @Modifier by:
 */
public class BeanUtil {
    public static Map<String, Object> objectToHash(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return objectMapper.convertValue(object, new TypeReference<Map<String, Object>>(){});
    }

    public static  <T> T hashToObject(Map<Object, Object> hash, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return objectMapper.convertValue(hash, clazz);
    }
}

package com.pineapple.pineapplelogistics.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.pineapple.pineapplelogistics.constriant.RedisConst.*;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 16:47 2023/5/3
 * @Modifier by:
 */
@Component
public class RedisTokenRepo implements PersistentTokenRepository {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        String series = token.getSeries();
        String key = getKey(LOGIN_KEY_PREFIX,series,"");
        String usernameKey = getKey(LOGIN_USERNAME_KEY_PREFIX, token.getUsername(), "");
        deleteIfPresent(usernameKey);
        deleteIfPresent(key);
        Map<String,String> hashmap = new HashMap<>();
        hashmap.put("username",token.getUsername());
        hashmap.put("token",token.getTokenValue());
        hashmap.put("date", String.valueOf(token.getDate()));
        redisTemplate.opsForHash().putAll(key,hashmap);
        redisTemplate.expire(key,LOGIN_REMEMBER_TTL, TimeUnit.DAYS);
        redisTemplate.opsForValue().set(usernameKey,series);
        redisTemplate.expire(usernameKey,LOGIN_REMEMBER_TTL,TimeUnit.DAYS);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        String key = getKey(LOGIN_TOKEN_PREFIX, series, "");
        if (Boolean.TRUE.equals(redisTemplate.hasKey(key))){
            redisTemplate.opsForHash().put(key,"token",tokenValue);
        }
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        String key = getKey(LOGIN_TOKEN_PREFIX, seriesId,"");
        List<Object> keys = new ArrayList<>();
        keys.add("username");
        keys.add("token");
        keys.add("date");
        List<Object> objs = redisTemplate.opsForHash().multiGet(key, keys);
        String username = (String) objs.get(0);
        String tokenValue = (String) objs.get(1);
        String date  = (String) objs.get(2);
        if (null == username || null == tokenValue || null == date){
            return null;
        }
        Long timestamp = Long.valueOf(date);
        Date dateTime = new Date(timestamp);
        return new PersistentRememberMeToken(username,seriesId,tokenValue,dateTime);
    }

    @Override
    public void removeUserTokens(String username) {
        String key = getKey(LOGIN_TOKEN_PREFIX, username, "");
        deleteIfPresent(key);
        String usernameKey = getKey(LOGIN_USERNAME_KEY_PREFIX, username, "");
        deleteIfPresent(usernameKey);
    }

    private  void deleteIfPresent(String key){
        if (Boolean.TRUE.equals(redisTemplate.hasKey(key))){
            redisTemplate.delete(key);
        }
    }
    private String getKey(String prefix,String series,String suffix){
        return prefix+series+suffix;
    }
}

package com.ruoyi.auth.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.ruoyi.common.redis.annotation.RedisEvict;
import com.ruoyi.common.utils.JwtUtil;

@Service("accessTokenService")
public class AccessTokenService
{
    @Autowired
    private StringRedisTemplate             redisTemplate;

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> ops;

    /**
     * 12小时后过期
     */
    private final static long               EXPIRE        = 3600 * 12;

    private final static String             ACCESS_TOKEN  = "access_token_";

    private final static String             ACCESS_USERID = "access_userid_";

    public Long queryByToken(String token)
    {
        return Long.valueOf(ops.get(ACCESS_TOKEN + token));
    }

    @RedisEvict(key = "user_perms",fieldKey="#userId")
    public Map<String, Object> createToken(long userId, String username, String password)
    {
        // 当前时间
        Date now = new Date();
        // 过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);
        // 生成token
        String token = JwtUtil.sign(username, password);
        // 保存或更新用户token
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("token", token);
        map.put("expire", expireTime.getTime() - System.currentTimeMillis());
//        expireToken(userId);
        ops.set(ACCESS_TOKEN + token, userId + "", EXPIRE, TimeUnit.SECONDS);
        ops.set(ACCESS_USERID + userId, token, EXPIRE, TimeUnit.SECONDS);
        return map;
    }

    public void expireToken(long userId)
    {
        String token = ops.get(ACCESS_USERID + userId);
        if (StringUtils.isNotBlank(token))
        {
            redisTemplate.delete(ACCESS_USERID + userId);
            redisTemplate.delete(ACCESS_TOKEN + token);
        }
    }
}
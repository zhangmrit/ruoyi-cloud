package com.ruoyi.auth.service;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.auth.entity.AccessToken;
import com.ruoyi.common.util.RedisUtils;

@Service("accessTokenService")
public class AccessTokenService
{
    @Autowired
    private RedisUtils          redis;

    /**
     * 12小时后过期
     */
    private final static int    EXPIRE        = 3600 * 12;

    private final static String ACCESS_TOKEN  = "access_token_";

    private final static String ACCESS_USERID = "access_userid_";

    public AccessToken queryByToken(String token)
    {
        return redis.get(ACCESS_TOKEN + token, AccessToken.class);
    }

    public AccessToken createToken(long userId)
    {
        // 当前时间
        Date now = new Date();
        // 过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);
        // 生成token
        String token = generateToken();
        // 保存或更新用户token
        AccessToken accessToken = new AccessToken();
        accessToken.setUserId(userId);
        accessToken.setToken(token);
        accessToken.setExpireTime(expireTime);
        expireToken(userId);
        redis.set(ACCESS_TOKEN + token, accessToken, EXPIRE);
        redis.set(ACCESS_USERID + userId, token, EXPIRE);
        return accessToken;
    }

    public void expireToken(long userId)
    {
        String token = redis.get(ACCESS_USERID + userId);
        if (StringUtils.isNotBlank(token))
        {
            redis.delete(ACCESS_USERID + userId);
            redis.delete(ACCESS_TOKEN + token);
        }
    }

    private String generateToken()
    {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
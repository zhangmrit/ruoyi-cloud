package com.ruoyi.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;

import com.ruoyi.auth.entity.AccessToken;
import com.ruoyi.auth.form.LoginForm;
import com.ruoyi.auth.service.AccessTokenService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.feign.ISysUserClient;

public class TokenController
{
    @Autowired
    private AccessTokenService tokenService;

    @Autowired
    private ISysUserClient     userClient;

    public R login(@RequestBody LoginForm form)
    {
        // 用户登录
        SysUser user = userClient.selectSysUserByUsername(form.getUsername());
        Assert.isNull(user, "手机号或密码错误");
        // 密码错误
        if (!user.getPassword().equals(DigestUtils.sha256Hex(form.getPassword())))
        {
            throw new RuntimeException("手机号或密码错误");
        }
        // 获取登录token
        AccessToken accessToken = tokenService.createToken(user.getUserId());
        Map<String, Object> map = new HashMap<>(2);
        map.put("token", accessToken.getToken());
        map.put("expire", accessToken.getExpireTime().getTime() - System.currentTimeMillis());
        return R.ok(map);
    }
}

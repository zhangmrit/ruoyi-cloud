package com.ruoyi.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.auth.entity.AccessToken;
import com.ruoyi.auth.form.LoginForm;
import com.ruoyi.auth.service.AccessTokenService;
import com.ruoyi.auth.service.SysPasswordService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.exception.RuoyiException;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.feign.ISysUserClient;

@RestController
public class TokenController
{
    @Autowired
    private AccessTokenService tokenService;

    @Autowired
    private ISysUserClient     userClient;
    
    @Autowired
    private SysPasswordService passwordService;

    @PostMapping("login")
    public R login(@RequestBody LoginForm form)
    {
        System.out.println(form);
        // 用户登录
        SysUser user = userClient.selectSysUserByUsername(form.getUsername());
        // 密码错误
        if (null==user||!passwordService.matches(user, form.getPassword()))
        {
            throw new RuoyiException("手机号或密码错误");
        }
        // 获取登录token
        AccessToken accessToken = tokenService.createToken(user.getUserId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", accessToken.getToken());
        map.put("expire", accessToken.getExpireTime().getTime() - System.currentTimeMillis());
        return R.ok(map);
    }

}

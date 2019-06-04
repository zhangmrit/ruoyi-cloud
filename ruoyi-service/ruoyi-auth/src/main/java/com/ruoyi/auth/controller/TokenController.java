package com.ruoyi.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        // 用户登录
        SysUser user = userClient.selectSysUserByUsername(form.getUsername());
        // 密码错误
        if (null == user || !passwordService.matches(user, form.getPassword()))
        {
            throw new RuoyiException("手机号或密码错误");
        }
        // 获取登录token
        return R.ok(tokenService.createToken(user.getUserId(), user.getLoginName(), user.getPassword()));
    }
}

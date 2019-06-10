package com.ruoyi.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.auth.form.LoginForm;
import com.ruoyi.auth.service.AccessTokenService;
import com.ruoyi.auth.service.SysLoginService;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.system.domain.SysUser;

@RestController
public class TokenController
{
    @Autowired
    private AccessTokenService tokenService;

    @Autowired
    private SysLoginService    sysLoginService;

    @PostMapping("login")
    public R login(@RequestBody LoginForm form)
    {
        // 用户登录
        SysUser user = sysLoginService.login(form.getUsername(), form.getPassword());
        // 获取登录token
        return R.ok(tokenService.createToken(user.getUserId(), user.getLoginName(), user.getPassword()));
    }

    @PostMapping("logout")
    public R logout(HttpServletRequest request)
    {
        Long userId = Long.valueOf(ServletUtils.getRequest().getHeader(Constants.USER_KEY));
        if (null != userId)
        {
            tokenService.expireToken(userId);
        }
        return R.ok();
    }
}

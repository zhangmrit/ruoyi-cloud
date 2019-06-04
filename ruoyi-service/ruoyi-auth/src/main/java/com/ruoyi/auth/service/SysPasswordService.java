package com.ruoyi.auth.service;

import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.Md5Utils;
import com.ruoyi.system.domain.SysUser;

@Service("sysPasswordService")
public class SysPasswordService
{
    public boolean matches(SysUser user, String newPassword)
    {
        return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
    }

    public String encryptPassword(String username, String password, String salt)
    {
        return Md5Utils.hash(username + password + salt);
    }
}
package com.ruoyi.system.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ruoyi.common.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.feign.factory.RemoteUserFallbackFactory;

/**
 * 用户 Feign服务层
 * 
 * @author zmr
 * @date 2019-05-20
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService
{
    @GetMapping("user/find/{username}")
    public SysUser selectSysUserByUsername(@PathVariable("username") String username);

    @PostMapping("user/update/login")
    public R updateUserLoginRecord(@RequestBody SysUser user);
}

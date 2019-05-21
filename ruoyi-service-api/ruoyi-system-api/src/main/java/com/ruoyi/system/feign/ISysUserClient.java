package com.ruoyi.system.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ruoyi.common.constant.ServiceNameConstants;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.feign.factory.SysUserClientFallbackFactory;

/**
 * 用户 Feign服务层
 * 
 * @author zmr
 * @date 2019-05-20
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = SysUserClientFallbackFactory.class)
public interface ISysUserClient
{
    String PREFIX = "user/";

    @GetMapping(PREFIX + "find/{username}")
    public SysUser selectSysUserByUsername(@PathVariable("username") String username);
}

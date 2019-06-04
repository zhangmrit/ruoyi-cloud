package com.ruoyi.system.feign;

import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ruoyi.common.constant.ServiceNameConstants;
import com.ruoyi.system.feign.factory.SysMenuClientFallbackFactory;

/**
 * 菜单 Feign服务层
 * 
 * @author zmr
 * @date 2019-05-20
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = SysMenuClientFallbackFactory.class)
public interface ISysMenuClient
{
    @GetMapping("menu/perms/{userId}")
    public Set<String> selectPermsByUserId(@PathVariable("userId") Long userId);
}

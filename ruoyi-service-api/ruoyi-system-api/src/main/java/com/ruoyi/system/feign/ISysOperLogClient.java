package com.ruoyi.system.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ruoyi.common.constant.ServiceNameConstants;
import com.ruoyi.system.domain.SysOperLog;
import com.ruoyi.system.feign.factory.SysOperLogClientFallbackFactory;

/**
 * 用户 Feign服务层
 * 
 * @author zmr
 * @date 2019-05-20
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = SysOperLogClientFallbackFactory.class)
public interface ISysOperLogClient
{
    @GetMapping("operLog/save")
    public void insertOperlog(@RequestBody SysOperLog operLog);
}

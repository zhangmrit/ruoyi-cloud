package com.ruoyi.system.feign.factory;

import org.springframework.stereotype.Component;

import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.feign.ISysUserClient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SysUserClientFallbackFactory implements FallbackFactory<ISysUserClient>
{
    @Override
    public ISysUserClient create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new ISysUserClient()
        {
            @Override
            public SysUser selectSysUserByUsername(String username)
            {
                return null;
            }
        };
    }
}

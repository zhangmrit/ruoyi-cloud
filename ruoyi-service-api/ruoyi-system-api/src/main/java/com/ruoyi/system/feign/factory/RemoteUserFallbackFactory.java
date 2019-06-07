package com.ruoyi.system.feign.factory;

import org.springframework.stereotype.Component;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.feign.RemoteUserService;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService>
{
    @Override
    public RemoteUserService create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new RemoteUserService()
        {
            @Override
            public SysUser selectSysUserByUsername(String username)
            {
                return null;
            }

            @Override
            public R updateUserInfo(SysUser user)
            {
                return R.error();
            }
        };
    }
}

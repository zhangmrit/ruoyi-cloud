package com.ruoyi.system.feign.factory;

import org.springframework.stereotype.Component;

import com.ruoyi.system.domain.SysOperLog;
import com.ruoyi.system.feign.ISysOperLogClient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SysOperLogClientFallbackFactory implements FallbackFactory<ISysOperLogClient>
{
    @Override
    public ISysOperLogClient create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new ISysOperLogClient()
        {
            @Override
            public void insertOperlog(SysOperLog operLog)
            {
            }
        };
    }
}

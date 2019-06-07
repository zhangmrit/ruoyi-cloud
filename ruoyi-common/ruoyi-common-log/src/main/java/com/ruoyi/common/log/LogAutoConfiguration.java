package com.ruoyi.common.log;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import com.ruoyi.common.log.aspect.OperLogAspect;
import com.ruoyi.common.log.listen.LogListener;
import com.ruoyi.system.feign.RemoteLogService;

import lombok.AllArgsConstructor;

@EnableAsync
@Configuration
@AllArgsConstructor
@ConditionalOnWebApplication
public class LogAutoConfiguration
{
    private final RemoteLogService logService;

    @Bean
    public LogListener sysOperLogListener()
    {
        return new LogListener(logService);
    }

    @Bean
    public OperLogAspect operLogAspect()
    {
        return new OperLogAspect();
    }
}
package com.ruoyi.common.log;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import com.ruoyi.common.log.aspect.OperLogAspect;
import com.ruoyi.common.log.event.SysOperLogListener;
import com.ruoyi.system.feign.ISysOperLogClient;

import lombok.AllArgsConstructor;

@EnableAsync
@Configuration
@AllArgsConstructor
@ConditionalOnWebApplication
public class LogAutoConfiguration
{
    private final ISysOperLogClient sysOperLogClient;

    @Bean
    public SysOperLogListener sysOperLogListener()
    {
        return new SysOperLogListener(sysOperLogClient);
    }

    @Bean
    public OperLogAspect operLogAspect()
    {
        return new OperLogAspect();
    }
}
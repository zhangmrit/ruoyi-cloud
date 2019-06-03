package com.ruoyi.common.log.event;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import com.ruoyi.system.domain.SysOperLog;
import com.ruoyi.system.feign.ISysOperLogClient;

import lombok.AllArgsConstructor;

/**
 * 异步监听日志事件
 */
@AllArgsConstructor
public class SysOperLogListener
{
    private final ISysOperLogClient sysOperLogClient;

    @Async
    @Order
    @EventListener(SysOperLogEvent.class)
    public void saveSysLog(SysOperLogEvent event)
    {
        SysOperLog sysOperLog = (SysOperLog) event.getSource();
        sysOperLogClient.insertOperlog(sysOperLog);
    }
}
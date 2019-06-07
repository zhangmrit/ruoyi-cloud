package com.ruoyi.common.log.listen;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import com.ruoyi.common.log.event.SysLogininforEvent;
import com.ruoyi.common.log.event.SysOperLogEvent;
import com.ruoyi.system.domain.SysLogininfor;
import com.ruoyi.system.domain.SysOperLog;
import com.ruoyi.system.feign.RemoteLogService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 异步监听日志事件
 */
@Slf4j
@AllArgsConstructor
public class LogListener
{
    private final RemoteLogService remoteLogService;

    @Async
    @Order
    @EventListener(SysOperLogEvent.class)
    public void listenOperLog(SysOperLogEvent event)
    {
        SysOperLog sysOperLog = (SysOperLog) event.getSource();
        remoteLogService.insertOperlog(sysOperLog);
        log.info("远程操作日志记录成功：{}", sysOperLog);
    }

    @Async
    @Order
    @EventListener(SysLogininforEvent.class)
    public void listenLoginifor(SysLogininforEvent event)
    {
        SysLogininfor sysLogininfor = (SysLogininfor) event.getSource();
        remoteLogService.insertLoginlog(sysLogininfor);
        log.info("远程访问日志记录成功：{}", sysLogininfor);
    }
}
package com.ruoyi.common.log.event;

import org.springframework.context.ApplicationEvent;

import com.ruoyi.system.domain.SysOperLog;

/**
 * 系统日志事件
 */
public class SysOperLogEvent extends ApplicationEvent
{
    //
    private static final long serialVersionUID = 8905017895058642111L;

    public SysOperLogEvent(SysOperLog source)
    {
        super(source);
    }
}
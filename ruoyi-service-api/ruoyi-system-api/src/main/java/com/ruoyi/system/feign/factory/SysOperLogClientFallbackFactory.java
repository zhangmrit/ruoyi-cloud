package com.ruoyi.system.feign.factory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ruoyi.common.core.page.PageDomain;
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
            public SysOperLog selectSysOperLogById(Integer operId)
            {
                return null;
            }
            
            @Override
            public List<SysOperLog> selectSysOperLogList(SysOperLog sysOperLog, PageDomain page)
            {
                return null;
            }
            
            @Override
            public int insertSysOperLog(SysOperLog sysOperLog)
            {
                return 0;
            }
            
            @Override
            public int updateSysOperLog(SysOperLog sysOperLog)
            {
                return 0;
            }
       
            @Override
            public int deleteSysOperLogByIds(String ids)
            {
                return 0;
            }
        };
    }
}

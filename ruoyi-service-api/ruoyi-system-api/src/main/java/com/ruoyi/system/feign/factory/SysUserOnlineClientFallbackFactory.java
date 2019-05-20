package com.ruoyi.system.feign.factory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.system.domain.SysUserOnline;
import com.ruoyi.system.feign.ISysUserOnlineClient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SysUserOnlineClientFallbackFactory implements FallbackFactory<ISysUserOnlineClient>
{
    @Override
    public ISysUserOnlineClient create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new ISysUserOnlineClient()
        {
            @Override
            public SysUserOnline selectSysUserOnlineById(String sessionId)
            {
                return null;
            }
            
            @Override
            public List<SysUserOnline> selectSysUserOnlineList(SysUserOnline sysUserOnline, PageDomain page)
            {
                return null;
            }
            
            @Override
            public int insertSysUserOnline(SysUserOnline sysUserOnline)
            {
                return 0;
            }
            
            @Override
            public int updateSysUserOnline(SysUserOnline sysUserOnline)
            {
                return 0;
            }
       
            @Override
            public int deleteSysUserOnlineByIds(String ids)
            {
                return 0;
            }
        };
    }
}

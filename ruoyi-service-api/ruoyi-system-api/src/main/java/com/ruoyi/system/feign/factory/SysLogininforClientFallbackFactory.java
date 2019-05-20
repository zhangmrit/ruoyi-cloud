package com.ruoyi.system.feign.factory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.system.domain.SysLogininfor;
import com.ruoyi.system.feign.ISysLogininforClient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SysLogininforClientFallbackFactory implements FallbackFactory<ISysLogininforClient>
{
    @Override
    public ISysLogininforClient create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new ISysLogininforClient()
        {
            @Override
            public SysLogininfor selectSysLogininforById(Integer infoId)
            {
                return null;
            }
            
            @Override
            public List<SysLogininfor> selectSysLogininforList(SysLogininfor sysLogininfor, PageDomain page)
            {
                return null;
            }
            
            @Override
            public int insertSysLogininfor(SysLogininfor sysLogininfor)
            {
                return 0;
            }
            
            @Override
            public int updateSysLogininfor(SysLogininfor sysLogininfor)
            {
                return 0;
            }
       
            @Override
            public int deleteSysLogininforByIds(String ids)
            {
                return 0;
            }
        };
    }
}

package com.ruoyi.system.feign.factory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.system.domain.SysConfig;
import com.ruoyi.system.feign.ISysConfigClient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SysConfigClientFallbackFactory implements FallbackFactory<ISysConfigClient>
{
    @Override
    public ISysConfigClient create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new ISysConfigClient()
        {
            @Override
            public SysConfig selectSysConfigById(Integer configId)
            {
                return null;
            }
            
            @Override
            public List<SysConfig> selectSysConfigList(SysConfig sysConfig, PageDomain page)
            {
                return null;
            }
            
            @Override
            public int insertSysConfig(SysConfig sysConfig)
            {
                return 0;
            }
            
            @Override
            public int updateSysConfig(SysConfig sysConfig)
            {
                return 0;
            }
       
            @Override
            public int deleteSysConfigByIds(String ids)
            {
                return 0;
            }
        };
    }
}

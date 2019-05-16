package com.ruoyi.system.feign.factory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.system.domain.SysOss;
import com.ruoyi.system.feign.ISysOssClient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SysOssClientFallbackFactory implements FallbackFactory<ISysOssClient>
{
    @Override
    public ISysOssClient create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new ISysOssClient()
        {
            @Override
            public SysOss selectSysOssById(Long id)
            {
                return null;
            }
            
            @Override
            public List<SysOss> selectSysOssList(SysOss sysOss,PageDomain page)
            {
                return null;
            }
            
            @Override
            public int insertSysOss(SysOss sysOss)
            {
                return 0;
            }
            
            @Override
            public int updateSysOss(SysOss sysOss)
            {
                return 0;
            }
       
            @Override
            public int deleteSysOssByIds(String ids)
            {
                return 0;
            }
        };
    }
}

package com.ruoyi.system.feign;

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
            public int update(SysOss sysOss)
            {
                return 0;
            }

            @Override
            public int save(SysOss ossEntity)
            {
                return 0;
            }

            @Override
            public List<SysOss> getList(SysOss sysOss, PageDomain page)
            {
                return null;
            }

            @Override
            public SysOss findById(Long ossId)
            {
                return null;
            }

            @Override
            public int deleteByIds(String ids)
            {
                return 0;
            }
        };
    }
}
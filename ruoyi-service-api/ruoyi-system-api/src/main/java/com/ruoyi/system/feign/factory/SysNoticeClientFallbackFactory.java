package com.ruoyi.system.feign.factory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.feign.ISysNoticeClient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SysNoticeClientFallbackFactory implements FallbackFactory<ISysNoticeClient>
{
    @Override
    public ISysNoticeClient create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new ISysNoticeClient()
        {
            @Override
            public SysNotice selectSysNoticeById(Integer noticeId)
            {
                return null;
            }
            
            @Override
            public List<SysNotice> selectSysNoticeList(SysNotice sysNotice, PageDomain page)
            {
                return null;
            }
            
            @Override
            public int insertSysNotice(SysNotice sysNotice)
            {
                return 0;
            }
            
            @Override
            public int updateSysNotice(SysNotice sysNotice)
            {
                return 0;
            }
       
            @Override
            public int deleteSysNoticeByIds(String ids)
            {
                return 0;
            }
        };
    }
}

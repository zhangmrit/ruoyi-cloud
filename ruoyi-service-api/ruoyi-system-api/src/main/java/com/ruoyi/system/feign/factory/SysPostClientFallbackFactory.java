package com.ruoyi.system.feign.factory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.feign.ISysPostClient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SysPostClientFallbackFactory implements FallbackFactory<ISysPostClient>
{
    @Override
    public ISysPostClient create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new ISysPostClient()
        {
            @Override
            public SysPost selectSysPostById(Integer postId)
            {
                return null;
            }
            
            @Override
            public List<SysPost> selectSysPostList(SysPost sysPost, PageDomain page)
            {
                return null;
            }
            
            @Override
            public int insertSysPost(SysPost sysPost)
            {
                return 0;
            }
            
            @Override
            public int updateSysPost(SysPost sysPost)
            {
                return 0;
            }
       
            @Override
            public int deleteSysPostByIds(String ids)
            {
                return 0;
            }
        };
    }
}

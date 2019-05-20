package com.ruoyi.system.feign.factory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.system.domain.SysUserPost;
import com.ruoyi.system.feign.ISysUserPostClient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SysUserPostClientFallbackFactory implements FallbackFactory<ISysUserPostClient>
{
    @Override
    public ISysUserPostClient create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new ISysUserPostClient()
        {
            @Override
            public SysUserPost selectSysUserPostById(Integer userId)
            {
                return null;
            }
            
            @Override
            public List<SysUserPost> selectSysUserPostList(SysUserPost sysUserPost, PageDomain page)
            {
                return null;
            }
            
            @Override
            public int insertSysUserPost(SysUserPost sysUserPost)
            {
                return 0;
            }
            
            @Override
            public int updateSysUserPost(SysUserPost sysUserPost)
            {
                return 0;
            }
       
            @Override
            public int deleteSysUserPostByIds(String ids)
            {
                return 0;
            }
        };
    }
}

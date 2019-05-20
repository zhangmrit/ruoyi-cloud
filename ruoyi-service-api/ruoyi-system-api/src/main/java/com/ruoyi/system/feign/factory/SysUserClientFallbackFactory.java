package com.ruoyi.system.feign.factory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.feign.ISysUserClient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SysUserClientFallbackFactory implements FallbackFactory<ISysUserClient>
{
    @Override
    public ISysUserClient create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new ISysUserClient()
        {
            @Override
            public SysUser selectSysUserById(Integer userId)
            {
                return null;
            }
            
            @Override
            public List<SysUser> selectSysUserList(SysUser sysUser, PageDomain page)
            {
                return null;
            }
            
            @Override
            public int insertSysUser(SysUser sysUser)
            {
                return 0;
            }
            
            @Override
            public int updateSysUser(SysUser sysUser)
            {
                return 0;
            }
       
            @Override
            public int deleteSysUserByIds(String ids)
            {
                return 0;
            }
        };
    }
}

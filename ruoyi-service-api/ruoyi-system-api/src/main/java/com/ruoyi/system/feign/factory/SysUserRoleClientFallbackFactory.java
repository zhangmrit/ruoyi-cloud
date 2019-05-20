package com.ruoyi.system.feign.factory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.feign.ISysUserRoleClient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SysUserRoleClientFallbackFactory implements FallbackFactory<ISysUserRoleClient>
{
    @Override
    public ISysUserRoleClient create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new ISysUserRoleClient()
        {
            @Override
            public SysUserRole selectSysUserRoleById(Integer userId)
            {
                return null;
            }
            
            @Override
            public List<SysUserRole> selectSysUserRoleList(SysUserRole sysUserRole, PageDomain page)
            {
                return null;
            }
            
            @Override
            public int insertSysUserRole(SysUserRole sysUserRole)
            {
                return 0;
            }
            
            @Override
            public int updateSysUserRole(SysUserRole sysUserRole)
            {
                return 0;
            }
       
            @Override
            public int deleteSysUserRoleByIds(String ids)
            {
                return 0;
            }
        };
    }
}

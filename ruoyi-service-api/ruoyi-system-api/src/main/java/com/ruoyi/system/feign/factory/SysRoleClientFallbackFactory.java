package com.ruoyi.system.feign.factory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.feign.ISysRoleClient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SysRoleClientFallbackFactory implements FallbackFactory<ISysRoleClient>
{
    @Override
    public ISysRoleClient create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new ISysRoleClient()
        {
            @Override
            public SysRole selectSysRoleById(Integer roleId)
            {
                return null;
            }
            
            @Override
            public List<SysRole> selectSysRoleList(SysRole sysRole, PageDomain page)
            {
                return null;
            }
            
            @Override
            public int insertSysRole(SysRole sysRole)
            {
                return 0;
            }
            
            @Override
            public int updateSysRole(SysRole sysRole)
            {
                return 0;
            }
       
            @Override
            public int deleteSysRoleByIds(String ids)
            {
                return 0;
            }
        };
    }
}

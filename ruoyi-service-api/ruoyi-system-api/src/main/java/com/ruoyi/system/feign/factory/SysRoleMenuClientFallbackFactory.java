package com.ruoyi.system.feign.factory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.system.domain.SysRoleMenu;
import com.ruoyi.system.feign.ISysRoleMenuClient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SysRoleMenuClientFallbackFactory implements FallbackFactory<ISysRoleMenuClient>
{
    @Override
    public ISysRoleMenuClient create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new ISysRoleMenuClient()
        {
            @Override
            public SysRoleMenu selectSysRoleMenuById(Integer roleId)
            {
                return null;
            }
            
            @Override
            public List<SysRoleMenu> selectSysRoleMenuList(SysRoleMenu sysRoleMenu, PageDomain page)
            {
                return null;
            }
            
            @Override
            public int insertSysRoleMenu(SysRoleMenu sysRoleMenu)
            {
                return 0;
            }
            
            @Override
            public int updateSysRoleMenu(SysRoleMenu sysRoleMenu)
            {
                return 0;
            }
       
            @Override
            public int deleteSysRoleMenuByIds(String ids)
            {
                return 0;
            }
        };
    }
}

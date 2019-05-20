package com.ruoyi.system.feign.factory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.system.domain.SysMenu;
import com.ruoyi.system.feign.ISysMenuClient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SysMenuClientFallbackFactory implements FallbackFactory<ISysMenuClient>
{
    @Override
    public ISysMenuClient create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new ISysMenuClient()
        {
            @Override
            public SysMenu selectSysMenuById(Integer menuId)
            {
                return null;
            }
            
            @Override
            public List<SysMenu> selectSysMenuList(SysMenu sysMenu, PageDomain page)
            {
                return null;
            }
            
            @Override
            public int insertSysMenu(SysMenu sysMenu)
            {
                return 0;
            }
            
            @Override
            public int updateSysMenu(SysMenu sysMenu)
            {
                return 0;
            }
       
            @Override
            public int deleteSysMenuByIds(String ids)
            {
                return 0;
            }
        };
    }
}

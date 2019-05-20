package com.ruoyi.system.feign.factory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.system.domain.SysRoleDept;
import com.ruoyi.system.feign.ISysRoleDeptClient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SysRoleDeptClientFallbackFactory implements FallbackFactory<ISysRoleDeptClient>
{
    @Override
    public ISysRoleDeptClient create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new ISysRoleDeptClient()
        {
            @Override
            public SysRoleDept selectSysRoleDeptById(Integer roleId)
            {
                return null;
            }
            
            @Override
            public List<SysRoleDept> selectSysRoleDeptList(SysRoleDept sysRoleDept, PageDomain page)
            {
                return null;
            }
            
            @Override
            public int insertSysRoleDept(SysRoleDept sysRoleDept)
            {
                return 0;
            }
            
            @Override
            public int updateSysRoleDept(SysRoleDept sysRoleDept)
            {
                return 0;
            }
       
            @Override
            public int deleteSysRoleDeptByIds(String ids)
            {
                return 0;
            }
        };
    }
}

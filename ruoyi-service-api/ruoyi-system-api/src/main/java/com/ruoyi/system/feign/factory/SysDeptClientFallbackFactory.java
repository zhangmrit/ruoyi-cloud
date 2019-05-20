package com.ruoyi.system.feign.factory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.feign.ISysDeptClient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SysDeptClientFallbackFactory implements FallbackFactory<ISysDeptClient>
{
    @Override
    public ISysDeptClient create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new ISysDeptClient()
        {
            @Override
            public SysDept selectSysDeptById(Integer deptId)
            {
                return null;
            }
            
            @Override
            public List<SysDept> selectSysDeptList(SysDept sysDept, PageDomain page)
            {
                return null;
            }
            
            @Override
            public int insertSysDept(SysDept sysDept)
            {
                return 0;
            }
            
            @Override
            public int updateSysDept(SysDept sysDept)
            {
                return 0;
            }
       
            @Override
            public int deleteSysDeptByIds(String ids)
            {
                return 0;
            }
        };
    }
}

package com.ruoyi.system.feign.factory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.system.domain.SysDictType;
import com.ruoyi.system.feign.ISysDictTypeClient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SysDictTypeClientFallbackFactory implements FallbackFactory<ISysDictTypeClient>
{
    @Override
    public ISysDictTypeClient create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new ISysDictTypeClient()
        {
            @Override
            public SysDictType selectSysDictTypeById(Integer dictId)
            {
                return null;
            }
            
            @Override
            public List<SysDictType> selectSysDictTypeList(SysDictType sysDictType, PageDomain page)
            {
                return null;
            }
            
            @Override
            public int insertSysDictType(SysDictType sysDictType)
            {
                return 0;
            }
            
            @Override
            public int updateSysDictType(SysDictType sysDictType)
            {
                return 0;
            }
       
            @Override
            public int deleteSysDictTypeByIds(String ids)
            {
                return 0;
            }
        };
    }
}

package com.ruoyi.system.feign.factory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.feign.ISysDictDataClient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SysDictDataClientFallbackFactory implements FallbackFactory<ISysDictDataClient>
{
    @Override
    public ISysDictDataClient create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new ISysDictDataClient()
        {
            @Override
            public SysDictData selectSysDictDataById(Integer dictCode)
            {
                return null;
            }
            
            @Override
            public List<SysDictData> selectSysDictDataList(SysDictData sysDictData, PageDomain page)
            {
                return null;
            }
            
            @Override
            public int insertSysDictData(SysDictData sysDictData)
            {
                return 0;
            }
            
            @Override
            public int updateSysDictData(SysDictData sysDictData)
            {
                return 0;
            }
       
            @Override
            public int deleteSysDictDataByIds(String ids)
            {
                return 0;
            }
        };
    }
}

package com.ruoyi.system.feign.factory;

import java.util.Set;

import org.springframework.stereotype.Component;

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
            public Set<String> selectPermsByUserId(Long userId)
            {
                return null;
            }
        };
    }
}

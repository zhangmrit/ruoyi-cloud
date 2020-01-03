package com.ruoyi.activiti.config;

import org.activiti.engine.impl.cfg.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Auther: Ace Lee
 * @Date: 2019/3/11 16:05
 */
@Component
public class MyIdGenerator implements IdGenerator
{
    @Override
    public String getNextId()
    {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }
}
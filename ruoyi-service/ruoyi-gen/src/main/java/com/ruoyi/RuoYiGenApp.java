package com.ruoyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.ruoyi.system.annotation.EnableRyFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.ruoyi.*.mapper")
@EnableRyFeignClients
public class RuoYiGenApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoYiGenApp.class, args);
    }
}

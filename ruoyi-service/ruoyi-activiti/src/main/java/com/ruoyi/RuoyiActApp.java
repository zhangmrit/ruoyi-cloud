package com.ruoyi;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.ruoyi.system.annotation.EnableRyFeignClients;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableDiscoveryClient
@MapperScan("com.ruoyi.*.mapper")
@EnableRyFeignClients
public class RuoyiActApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoyiActApp.class, args);
    }
}

package com.ruoyi.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAdminServer
@SpringBootApplication
public class RuoyiMonitorApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoyiMonitorApp.class, args);
    }
}
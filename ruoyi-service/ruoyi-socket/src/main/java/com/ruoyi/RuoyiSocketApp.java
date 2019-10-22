package com.ruoyi;

import com.ruoyi.socket.server.netty.NettyServer;
import com.ruoyi.socket.annotation.EnableRyFeignClients;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

/**
 * Spring Java Configuration and Bootstrap
 *
 * @author TP
 */
@RequiredArgsConstructor
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableRyFeignClients
public class RuoyiSocketApp {

    private final NettyServer tcpServer;

    public static void main(String[] args) {
        SpringApplication.run(RuoyiSocketApp.class, args);
    }

    /**
     * This can not be implemented with lambda, because of the spring framework limitation
     * (https://github.com/spring-projects/spring-framework/issues/18681)
     *
     * @return
     */
    @SuppressWarnings({"Convert2Lambda"})
    @Bean
    public ApplicationListener<ApplicationReadyEvent> readyEventApplicationListener() {
        return new ApplicationListener<ApplicationReadyEvent>() {
            @Override
            public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
                tcpServer.start();
            }
        };
    }
}
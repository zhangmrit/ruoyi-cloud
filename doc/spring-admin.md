##### monitor

使用springadmin完成,目前只是最简单的用法

坑点比较多

1. 查看日志功能

   ```
   #该功能需和日志配置结合
   logging:
     file: /var/log/sample-boot-application.log
     #file： fileDir
     #还可以加path 请自行查阅
     pattern:
       file: clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} %clr(%5p) %clr(${PID}){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n%wEx
       
   # 暴露监控端点
   management:
     endpoints:
       web:
         exposure:
           include: '*'
     endpoint:
       logfile:
         enabled:      
       
       
   ```

2. 修改日志级别功能

   需要添加一些包和配置

3. security安全相关

   ```
   #添加对应的配置
   spring:
     security:
       user:
         name: "admin"
         password: "admin"
   
   eureka:
     instance:
         leaseRenewalIntervalInSeconds: 10
         health-check-url-path: /actuator/health
         prefer-ip-address: true
         metadata-map:
               user.name: ${spring.security.user.name}
               user.password: ${spring.security.user.password}
               
   ```

4. 邮件通知

   我可能暂时不需要这个功能就没加

   
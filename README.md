## 平台简介

本项目FORK自  [若依/RuoYi](https://gitee.com/y_project/RuoYi)

蓝本是[zhangmrit/Ruoyi](https://gitee.com/zhangmrit/RuoYi)

先绑定host：127.0.0.1 eureka7001.com

如果要使用eureka集群，请依次绑定eureka7002.com,eureka7003.com后修改各项目中的注释部分

```
ruoyi-cloud
|
├──ruoyi-common --通用包
|  |
|  ├──ruoyi-common-core --核心工具包
|  |
|  ├──ruoyi-common-redis --redis工具包
|  |
|  ├──ruoyi-common-log --日志工具包
|  |
|  ├──ruoyi-common-auth --权限工具包
|
├──ruoyi-config --cloud统一配置中心
|
├──ruoyi-eureka --注册中心
|
├──ruoyi-gateway --zuul网关
|
├──ruoyi-service-api --服务api模块
|  |
|  ├──ruoyi-system-api --系统业务api
|
├──ruoyi-service --微服务
|  |
|  ├──ruoyi-system --系统业务
|  |
|  ├──ruoyi-auth --授权中心
|
├──ruoyi-tool --工具
|  |
|  ├──ruoyi-monitor --监控中心
|  |
|  ├──ruoyi-generator --代码生成工具
|
├──ruoyi-ant --前端 使用ant design框架

```





启动顺序：
- eureka
- config
- gateway
- system
- auth

菜单sql有增加字段，以上传到sql文件
该分支是ant分支，前端使用ant-design-vue 项目地址 [ruoyi-ant](https://gitee.com/zhangmrit/ruoyi-ant)

monitor使用springadmin完成,目前只是最简单的用法

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

   



欢迎pr或者加入，给个star是最好的鞭策


## 平台简介

本项目FORK自  [若依/RuoYi](https://gitee.com/y_project/RuoYi)

蓝本是[zhangmrit/Ruoyi](https://gitee.com/zhangmrit/RuoYi)

先绑定host：127.0.0.1 eureka7001.com

如果要使用eureka集群，请依次绑定eureka7002.com,eureka7003.com后修改各项目中的注释部分

```
ruoyi-cloud
|
├──ruoyi-common --通用工具包
|
├──ruoyi-config --cloud统一配置中心
|
├──ruoyi-eureka --注册中心
|
├──ruoyi-front --工程前端相关
|  |
|  ├──ruoyi-admin --管理后台
|
├──ruoyi-gateway --zuul网关
|
├──ruoyi-service-api --服务api模块
|  |
|  ├──ruoyi-system-api --系统业务api
|
├──ruoyi-service --服务提供者
|  |
|  ├──ruoyi-system --系统业务提供者
|
├──ruoyi-generator --代码生成工具

```





启动顺序：
- eureka
- gateway
- config
- system
- admin

菜单sql有增加字段，以上传到doc文件
该分支是ant分支，前端使用ant-design-vue 项目地址 [ruoyi-ant](https://gitee.com/zhangmrit/ruoyi-ant)

欢迎pr或者加入，给个star是最好的鞭策


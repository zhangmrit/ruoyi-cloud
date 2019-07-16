## 平台简介
若依spring-cloud实现

### [文档](http://doc.rycloud.zmrit.com)

#### 分支说明

- **master** spring原生方式，使用eureka做注册中心和spring config做配置中心
- **nacos** 集成spring-cloud-alibaba 使用nacos做注册中心和配置中心



本项目FORK自  [若依/RuoYi](https://gitee.com/y_project/RuoYi)

蓝本是[zhangmrit/Ruoyi](https://gitee.com/zhangmrit/RuoYi)

依次绑定host：

127.0.0.1 gateway.com

因项目使用spring-cloud-alibaba nacos作为注册中心，移除了eureka和config


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
├──ruoyi-gateway --网关
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
- 搭建nacos serve，导入sql/nacos.sql并[配置持久化](https://nacos.io/zh-cn/docs/deployment.html)启动，如需帮助，请参考[nacos文档](https://nacos.io/zh-cn/docs/quick-start-spring-cloud.html)
- gateway
- system
- auth

问题汇总

1. 配置nacos.config必须在bootstrap.yml或者bootstrap.properties,原因见：[nacos wiki](https://github.com/spring-cloud-incubator/spring-cloud-alibaba/wiki/Nacos-config)
2. gateway中配置增加了自动转大写
3. jar启动时，nacos远程配置内容不能有注释，ide中启动没有问题
4. 本实例把nacos的配置都放在nacos.sql，如果你不需要持久化，也可以从master分支config中获取，配置内容是一样的

菜单sql有增加字段，以上传到sql文件
该分支是ant分支，前端使用ant-design-vue 项目地址 [ruoyi-ant](https://gitee.com/zhangmrit/ruoyi-ant)

monitor使用springadmin完成,目前只是最简单的用法,[详戳](https://gitee.com/zhangmrit/ruoyi-cloud/blob/ant/doc/spring-admin.md)

## RuoYi Cloud交流群

QQ群：  [![加入QQ群](https://img.shields.io/badge/755109875-blue.svg)](https://jq.qq.com/?_wv=1027&k=5JGXHPD)  点击按钮入群。


欢迎pr或者加入，给个star是最好的鞭策

##  请作者喝杯咖啡或者建设演示服务器

###### 微信 or 支付宝
<img src="https://gitee.com/zhangmrit/img/raw/master/contribute/wechat.png"/>
<img src="https://gitee.com/zhangmrit/img/raw/master/contribute/alipay.png"/>


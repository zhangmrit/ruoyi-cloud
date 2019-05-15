## 平台简介

本项目FORK自  [若依/RuoYi](https://gitee.com/y_project/RuoYi)
暂时花了几分钟时间把自己整合过的功能传上来：
###### 集成jwt
应很多同学要求写了一个jwt的模块，详情ruoyi-app，若需要使用`shiro-jwt`，见[shiro-jwt分支](https://gitee.com/zhangmrit/RuoYi/tree/shiro-jwt/)
###### 多数据源切面
删了多数据源注解，改成根据方法名自动切换，默认主从分离（当然主从地址一样），当只有一个数据源的时候虽然会打印日志，实际主从还是同一个，膈应就自己改一下
###### 集成通用mapper
一开始想整合mybatis-plus，这玩意太重了，而且crud和本项目很多地方八字不合。mapper继承com.ruoyi.common.base.BaseMapper<T>后就可以策马奔腾啦，谁用谁知道
###### 控制台日志分等级彩色渲染和多环境修改
具体看logback-spring.xml和application.xml改动
###### 集成七牛云，阿里云，腾讯云OSS
- 先去七牛注册一下（推荐，10g免费空间）,演示系统设置了1天删除文件
- 详见sql/oss.sql,在上传页面配置好相关参数即可使用
- 阿里云配置问题：长度必须在 1-1023字节之间，而且不能包含回车、换行、以及xml1.0不支持的字符，同时也不能以“/”或者“\”开头。
- 集成富文本编辑器上传，列表中显示预览图，支持点击放大
###### 集成省市区三级联动
- 演示distpicker插件
- 数据库驱动方式
###### 静态资源动态url
- 实际生产中可能前后端分离，防止缓存css,js链接可以这么写,例：
```
<script th:src="${urls.getForLookupPath('/ruoyi/js/ry-ui.js')}"></script>
```
###### druid去除广告
- 替换doc文件中的jar即可
###### 增加分页栏跳页插件
- options中定义:`showPageGo:true`
- 参见演示系统中系统管理→文件管理
- 该功能已经pr通过
## 在线体验
> admin/admin123  

演示地址： http://ruoyi.zmrit.com
 
若依演示地址：http://ruoyi.vip  

文档地址：http://doc.ruoyi.vip


一直想做一款后台管理系统，看了很多优秀的开源项目但是发现没有合适的。于是利用空闲休息时间开始自己写了一套后台系统。如此有了若依。她可以用于所有的Web应用程序，如网站管理后台，网站会员中心，CMS，CRM，OA。所有前端后台代码封装过后十分精简易上手，出错概率低。同时支持移动客户端访问。系统会陆续更新一些实用功能。

性别男，若依是给还没有出生女儿取的名字（寓意：你若不离不弃，我必生死相依）

若依基于hplus和inspinia两套后台系统模板开发。有需要可自行到群内下载。

> 如需单应用，请移步 [RuoYi-fast](https://gitee.com/y_project/RuoYi-fast)  `(保持同步更新)`，如需Oracle版本，请移步 [RuoYi-oracle](http://doc.ruoyi.vip/#/standard/xmkz)  `(不定时更新)`

> 阿里云通用云产品1888优惠券 ：[点我领取](https://promotion.aliyun.com/ntms/yunparter/invite.html?userCode=brki8iof)&nbsp;&nbsp;&nbsp;&nbsp;腾讯云通用云产品2860优惠券 ：[点我领取](https://cloud.tencent.com/redirect.php?redirect=1025&cps_key=198c8df2ed259157187173bc7f4f32fd&from=console)&nbsp;&nbsp;`(仅限新用户)`

## 内置功能

1.  用户管理：用户是系统操作者，该功能主要完成系统用户配置。
2.  部门管理：配置系统组织机构（公司、部门、小组），树结构展现支持数据权限。
3.  岗位管理：配置系统用户所属担任职务。
4.  菜单管理：配置系统菜单，操作权限，按钮权限标识等。
5.  角色管理：角色菜单权限分配、设置角色按机构进行数据范围权限划分。
6.  字典管理：对系统中经常使用的一些较为固定的数据进行维护。
7.  参数管理：对系统动态配置常用参数。
8.  通知公告：系统通知公告信息发布维护。
9.  操作日志：系统正常操作日志记录和查询；系统异常信息日志记录和查询。
10. 登录日志：系统登录日志记录查询包含登录异常。
11. 在线用户：当前系统中活跃用户状态监控。
12. 定时任务：在线（添加、修改、删除)任务调度包含执行结果日志。
13. 代码生成：前后端代码的生成（java、html、xml、sql)支持CRUD下载 。
14. 系统接口：根据业务代码自动生成相关的api接口文档。
15. 服务监控：监视当前系统CPU、内存、磁盘、堆栈等相关信息。
16. 在线构建器：拖动表单元素生成相应的HTML代码。
17. 连接池监视：监视当前系统数据库连接池状态，可进行分析SQL找出系统性能瓶颈。
## 在线体验
> admin/admin123  
> 陆陆续续收到一些打赏，为了更好的体验已用于演示服务器升级。谢谢各位小伙伴。

演示地址：http://ruoyi.vip  

文档地址：http://doc.ruoyi.vip

## 演示图

<table>
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/25b5e333768d013d45a990c152dbe4d9d6e.jpg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/5ac52ccc07a59f12205948c9408791f5c5b.jpg"/></td>
    </tr>
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/66f8b5b24720dabe0e11ae84bd1ad4b038e.jpg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/a46f34786bc9fc400697b6f3677be5bb3f0.jpg"/></td>
    </tr>
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/6010201b078dbc9e1d8c09c6a3e53f4344c.jpg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/058928ad3a6e6de67b43d62d42dbf071355.jpg"/></td>
    </tr>
	<tr>
        <td><img src="https://oscimg.oschina.net/oscnet/21acdcade5e306f2c5d7ae26993b4e6bd06.jpg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/ad5ea3c4c2ea2e91d1f05f6cc384cbad2a1.jpg"/></td>
    </tr>	 
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/6ca845cca1701fbf71881efe4f341c82f99.jpg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/e3aeb8fff585594f6e947218e14f2806ea1.jpg"/></td>
    </tr>
	<tr>
        <td><img src="https://oscimg.oschina.net/oscnet/197ddc2fdffc27020f8624bd7ca1a971f61.jpg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/a2dabea752d7d70aede20908dee0b419829.jpg"/></td>
    </tr>
	<tr>
        <td><img src="https://oscimg.oschina.net/oscnet/c5699c5726b5aebde71a37bb5163d840bc2.jpg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/24740d59377e826d0d8664ebad66dc84abd.jpg"/></td>
    </tr>
	<tr>
        <td><img src="https://oscimg.oschina.net/oscnet/bbe7fe1048d29217ba73bd3ed88d6743b55.jpg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/5f3d39a141f21f81b90536f391b8408f1fa.jpg"/></td>
    </tr>
</table>


## 若依交流群

QQ群： [![加入QQ群](https://img.shields.io/badge/已满-1389287-blue.svg)](https://jq.qq.com/?_wv=1027&k=5HBAaYN)  [![加入QQ群](https://img.shields.io/badge/已满-1679294-blue.svg)](https://jq.qq.com/?_wv=1027&k=5cHeRVW)  [![加入QQ群](https://img.shields.io/badge/已满-1529866-blue.svg)](https://jq.qq.com/?_wv=1027&k=53R0L5Z)  [![加入QQ群](https://img.shields.io/badge/已满-1772718-blue.svg)](https://jq.qq.com/?_wv=1027&k=5g75dCU)  [![加入QQ群](https://img.shields.io/badge/1366522-blue.svg)](https://jq.qq.com/?_wv=1027&k=58cPoHA)  点击按钮入群。
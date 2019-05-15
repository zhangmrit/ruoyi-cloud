### ruoyi常见问题
#### Q:jar运行方式没有问题，部署到tomcat出现404
###### A:多半是因为没有添加`RuoYiServletInitializer`类，建议同步最新代码或者添加该类

#### Q:有没有整合mybatis-plus，通用mapper，oss（对象存储），jwt（api,app开发），shiro-redis的？
###### A:先去文档扩展里面看看，大多数已经在了，[http://doc.ruoyi.vip/#/standard/ry05](http://doc.ruoyi.vip/#/standard/ry05)

#### Q:类似`type=${@dict.getType('sys_normal_disable')}`的代码怎么直接调用service的
###### A:Thymeleaf可以通过@beanName访问Spring应用上下文中注册的bean
如` <div th:text="${@myservice.hello()}">...</div>`
在这个例子中，`@myservice`就是在上下文中注册的`Spring Bean`

```
@Service("myservice")
public class MyService {
    
    public void hello() {
        System.out.println("Hello!")
    }
}
```

#### Q:提示`xx.table not exist`
###### A:一开始导入要执行2个sql文件，`ryxxx.sql`和`quartz.sql`

#### Q:数据库枚举类型代码生成报错
###### A:在CommonMap.java加一条javaTypeMap.put("enum", "String")

#### Q:访问不到对应的template
###### A:检查是否使用了绝对路径，即是否在路径前添加了'/'

#### Q:我要配置一个不被拦截的url，要怎么配
###### A:在`ShiroConfig`类中的`shiroFilterFactoryBean`方法添加

```
filterChainDefinitionMap.put("your url", "anon");
```

#### Q:json的时间格式化怎么弄？
###### A:ruoyi默认使用jackson
方法一：在字段上加注解

```
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date              createTime;
```

方法二：添加全局配置
application中配置
```
spring
    jackson
        date-format=yyyy-MM-dd HH:mm:ss
        time-zone=GMT+8
```
如果在页面上要格式化时间 ps: *还是$看使用场景

```
*{#dates.format(createTime, 'yyyy-MM-dd HH:mm:ss')}
```

如果在`excel`导出里

```
 @Excel(dateFormat = "yyyy-MM-dd HH:mm:ss")
```


#### Q:日期选择插件怎么用？怎么精确到时分秒？
###### A:默认使用layui.laydate,请参阅相关[文档](https://www.layui.com/doc/modules/laydate.html) ,或者自行使用其他插件

#### Q:上传的文件怎么映射url的？
###### A:详见`ResourcesConfig`，相当于把`"/profile/**"`映射到磁盘，用过`nginx`的同学是不是很熟悉呢

#### Q:为什么我新建的表生成代码里找不到？
###### A:99%的可能是表没有加注释，是表注释而不是字段注释原因见
```
<select id="selectTableByName" parameterType="String" resultMap="TableInfoResult">
	<include refid="selectGenVo"/>
	where table_comment <![CDATA[ <> ]]> '' and table_schema = (select database())
	and table_name = #{tableName}
</select>
```

#### Q:列字段内容过长，表格被撑乱，怎么破？
###### A:默认使用的是`bootstraptable`
1. 给tabel添加样式 `style="table-layout:fixed"`
 
2. js给td添加css样式： 

```
field : 'remark', 
title : '备注', 
cellStyle:{ 
    css:{ 
        "overflow": "hidden", 
        "text-overflow": "ellipsis", 
        "white-space": "nowrap" 
    } 
}
```



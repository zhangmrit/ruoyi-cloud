package com.ruoyi.activiti.config;

import javax.sql.DataSource;

import org.activiti.engine.DynamicBpmnService;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.ruoyi.activiti.cover.ICustomProcessDiagramGenerator;

/**
 * <p>File：ActivitiConfig.java</p>
 * <p>Title: activit配置类</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020 2020年1月15日 下午6:34:17</p>
 * <p>Company: zmrit.com </p>
 * @author zmr
 * @version 1.0
 */
@Configuration
public class ActivitiConfig
{
    @Autowired
    private ICustomProcessDiagramGenerator customProcessDiagramGenerator;

    // 流程配置
    @Bean
    public ProcessEngineConfigurationConfigurer processEngineConfigurationConfigurer(DataSource dataSource,
            PlatformTransactionManager transactionManager)
    {
        ProcessEngineConfigurationConfigurer configurer = new ProcessEngineConfigurationConfigurer()
        {
            @Override
            public void configure(SpringProcessEngineConfiguration processEngineConfiguration)
            {
                processEngineConfiguration.setDataSource(dataSource);
                // databaseSchemaUpdate：允许在流程引擎启动和关闭时设置处理数据库模式的策略。 
                // false（默认）：创建流程引擎时检查数据库模式的版本是否与函数库要求的匹配，如果版本不匹配就会抛出异常。
                // true：构建流程引擎时，执行检查，如果有必要会更新数据库模式。如果数据库模式不存在，就创建一个。
                // create - 引擎启动时创建表；
                // create-drop：创建流程引擎时创建数据库模式，关闭流程引擎时删除数据库模式。
                // drop-create - 引擎启动时先删除表再重新创建表。
                processEngineConfiguration.setDatabaseSchemaUpdate("true");
                processEngineConfiguration.setDatabaseType("mysql");
                // id策略 流程图如果需要追踪，只有默认id策略可以结节连线问题
                // processEngineConfiguration.setIdGenerator(idGenerator);
                processEngineConfiguration.setTransactionManager(transactionManager);
                // 自定义字体
                processEngineConfiguration.setActivityFontName("宋体");
                processEngineConfiguration.setAnnotationFontName("宋体");
                processEngineConfiguration.setLabelFontName("宋体");
                // 自定义流程图画笔
                processEngineConfiguration.setProcessDiagramGenerator(customProcessDiagramGenerator);
            }
        };
        return configurer;
    }

    // 流程引擎，与spring整合使用factoryBean
    @Bean
    public ProcessEngineFactoryBean processEngine(ProcessEngineConfiguration processEngineConfiguration)
    {
        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean
                .setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
        return processEngineFactoryBean;
    }

    // 八大接口
    @Bean
    public RepositoryService repositoryService(ProcessEngine processEngine)
    {
        return processEngine.getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService(ProcessEngine processEngine)
    {
        return processEngine.getRuntimeService();
    }

    @Bean
    public TaskService taskService(ProcessEngine processEngine)
    {
        return processEngine.getTaskService();
    }

    @Bean
    public HistoryService historyService(ProcessEngine processEngine)
    {
        return processEngine.getHistoryService();
    }

    @Bean
    public FormService formService(ProcessEngine processEngine)
    {
        return processEngine.getFormService();
    }

    @Bean
    public IdentityService identityService(ProcessEngine processEngine)
    {
        return processEngine.getIdentityService();
    }

    @Bean
    public ManagementService managementService(ProcessEngine processEngine)
    {
        return processEngine.getManagementService();
    }

    @Bean
    public DynamicBpmnService dynamicBpmnService(ProcessEngine processEngine)
    {
        return processEngine.getDynamicBpmnService();
    }
    // 八大接口 end
}

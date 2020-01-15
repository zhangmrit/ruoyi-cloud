package com.ruoyi.activiti.config;

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
    private MyIdGenerator idGenerator;
    
    @Autowired
    private ICustomProcessDiagramGenerator customProcessDiagramGenerator;

    // 流程配置
    @Bean
    public ProcessEngineConfigurationConfigurer processEngineConfigurationConfigurer()
    {
        ProcessEngineConfigurationConfigurer configurer = new ProcessEngineConfigurationConfigurer()
        {
            @Override
            public void configure(SpringProcessEngineConfiguration processEngineConfiguration)
            {
                processEngineConfiguration.setIdGenerator(idGenerator);
                processEngineConfiguration.setActivityFontName("宋体");
                processEngineConfiguration.setAnnotationFontName("宋体");
                processEngineConfiguration.setLabelFontName("宋体");
                //自定义流程图画笔
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

package com.ruoyi.activiti.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * process流程实例监听类
 */
@Component
@Slf4j
public class MyProcessExecutionListener implements ExecutionListener
{
    //
    private static final long serialVersionUID = 6173579003703470015L;

    @Override
    public void notify(DelegateExecution execution) throws Exception
    {
        String eventName = execution.getEventName();
        String procInstId = execution.getProcessInstanceId();
        // String businessKey = execution.getProcessBusinessKey();
        // start
        if ("start".equals(eventName))
        {
            log.info("流程实例[{}]启动", procInstId);
        }
        else if ("end".equals(eventName))
        {
            log.info("流程实例[{}]结束", procInstId);
        }
    }
}

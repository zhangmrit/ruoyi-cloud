package com.ruoyi.activiti.listener;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

/**
 *
 * 流程实例监听类
 *
 * @auther: Ace Lee
 * @date: 2019/3/8 11:57
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
        // start
        if ("start".equals(eventName))
        {
            log.info("==================start==================");
        }
        else if ("end".equals(eventName))
        {
            log.info("==================end==================");
        }
    }
}

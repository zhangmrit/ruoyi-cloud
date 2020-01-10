package com.ruoyi.activiti.listener;

import java.util.Set;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * 节点任务完成监听类
 *
 * @auther: Ace Lee
 * @date: 2019/3/8 11:57
 */
@Component
@Slf4j
public class MyTaskCompletedListener implements TaskListener
{
    //
    private static final long serialVersionUID = 5808415173145957468L;

    // 监听任务的事件
    @Override
    public void notify(DelegateTask delegateTask)
    {
        String assignee = delegateTask.getAssignee();
        String eventName = delegateTask.getEventName();
        String name = delegateTask.getName();
        String processInstanceId = delegateTask.getProcessInstanceId();
        // create: 任务被创建，并且所有的属性都被设置好后。
        if ("create".equals(eventName))
        {
            log.info("一个任务[{}]被创建了,由[{}]负责办理,流程实例编号[{}]", name, assignee, processInstanceId);
        }
        // assignment: 任务被委派给某人后。
        // 注意:当流程执行到达一个userTask时，会先触发一个assignment事件，再触发create事件。
        else if ("assignment".equals(eventName))
        {
            log.info("任务[{}]委派给[{}],流程实例编号为[{}]", name, assignee, processInstanceId);
        }
        // complete:在任务完成后，且被从运行时数据（runtime data）中删除前触发。
        else if ("complete".equals(eventName))
        {
            log.info("任务[{}]由{}执行完成,流程实例编号为:{}", name, assignee, processInstanceId);
            Set<String> variableNames = delegateTask.getVariableNames();
            for (String key : variableNames)
            {
                Object value = delegateTask.getVariable(key);
                log.info("参数{}={}", key, value);
            }
        }
        // delete: 在任务将要被删除之前发生。注意，当任务通过completeTask完成任务时，它也会被执行
        else if ("delete".equals(eventName))
        {
            log.info("任务[{}]将被删除,流程实例编号为[{}]", name, processInstanceId);
        }
    }
}

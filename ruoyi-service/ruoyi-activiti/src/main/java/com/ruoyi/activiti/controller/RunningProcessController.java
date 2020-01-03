package com.ruoyi.activiti.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.activiti.vo.RuExecution;
import com.ruoyi.activiti.vo.RuTask;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.PageDomain;

import lombok.extern.slf4j.Slf4j;

/**
 * 运行中的流程
 *
 * @Auther: Ace Lee
 * @Date: 2019/3/5 15:04
 */
@Slf4j
@RestController
@RequestMapping("runs")
public class RunningProcessController extends BaseController
{
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService    taskService;

    /**
     * 挂起、激活流程实例
     */
    @RequestMapping(value = "update/{state}/{processInstanceId}")
    public R updateState(@PathVariable("state") String state,
            @PathVariable("processInstanceId") String processInstanceId)
    {
        if (state.equals("active"))
        {
            runtimeService.activateProcessInstanceById(processInstanceId);
            log.info("已激活ID为:{}的流程实例", processInstanceId);
        }
        else if (state.equals("suspend"))
        {
            runtimeService.suspendProcessInstanceById(processInstanceId);
            log.info("已挂起ID为:{}的流程实例", processInstanceId);
        }
        return R.ok();
    }

    /**
     * 获取任务列表
     *
     * @param rowSize
     * @param page
     * @return
     */
    @RequestMapping(value = "tasks")
    public R tasks(PageDomain page)
    {
        List<Task> tasks = taskService.createTaskQuery().listPage(page.getPageSize() * (page.getPageNum() - 1),
                page.getPageSize());
        long count = runtimeService.createExecutionQuery().count();
        List<RuTask> list = new ArrayList<>();
        for (Task task : tasks)
        {
            list.add(new RuTask(task));
        }
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("rows", list);
        m.put("pageNum", page.getPageNum());
        m.put("total", count);
        return R.ok(m);
    }

    @RequestMapping(value = "list")
    public R getList(PageDomain page)
    {
        List<Execution> executions = runtimeService.createExecutionQuery().listPage(page.getPageSize() * (page.getPageNum() - 1),
                page.getPageSize());
        long count = runtimeService.createExecutionQuery().count();
        List<RuExecution> list = new ArrayList<>();
        for (Execution execution : executions)
        {
            list.add(new RuExecution(execution));
        }
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("rows", list);
        m.put("pageNum", page.getPageNum());
        m.put("total", count);
        return R.ok(m);
    }
}

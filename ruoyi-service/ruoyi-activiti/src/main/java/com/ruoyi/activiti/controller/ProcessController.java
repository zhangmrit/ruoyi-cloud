package com.ruoyi.activiti.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.domain.R;

import lombok.extern.slf4j.Slf4j;

/**
 * 流程管理
 *
 */
@Slf4j
@RestController
@RequestMapping("process")
public class ProcessController
{
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService    runtimeService;

    /**
     * 查看流程图
     *
     */
    @GetMapping("show")
    public void show(@RequestParam("did") String did, @RequestParam("ext") String ext,
            HttpServletResponse httpServletResponse) throws IOException
    {
        if (StringUtils.isEmpty(did) || StringUtils.isEmpty(ext))
        {
            return;
        }
        InputStream in = null;
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(did)
                .singleResult();
        if (".png".equalsIgnoreCase(ext))
        {
            in = repositoryService.getProcessDiagram(processDefinition.getId());
        }
        else if (".bpmn".equalsIgnoreCase(ext))
        {
            in = repositoryService.getResourceAsStream(did, processDefinition.getResourceName());
        }
        OutputStream out = null;
        byte[] buf = new byte[1024];
        int legth = 0;
        try
        {
            out = httpServletResponse.getOutputStream();
            while ((legth = in.read(buf)) != -1)
            {
                out.write(buf, 0, legth);
            }
        }
        finally
        {
            if (in != null)
            {
                in.close();
            }
            if (out != null)
            {
                out.close();
            }
        }
    }

    /**
     * 启动一个流程
     *
     * 参数：
     *      key:  流程定义KEY
     *
     *      bussId：  业务对象序号
     *      bussName：业务对象名称
     *      bussType：业务对象类型
     *      startUserId: 发起人id
     *      startUnitId: 发起人单位id
     *
     * @auther: Ace Lee
     * @date: 2019/3/5 15:24
     */
    @PostMapping("run/{key}")
    public R run(@PathVariable String key, @RequestBody Map<String, Object> variables)
    {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, variables);
        log.info("启动一个流程实例，id为：{}", processInstance.getId());
        return R.data(processInstance.getId());
    }

    /**
     * 挂起、激活流程实例
     */
    @RequestMapping(value = "update/{processId}/{state}")
    public R updateState(@PathVariable("state") String state, @PathVariable("processId") String processId)
    {
        if (state.equals("active"))
        {
            // 一并激活流程实例
            repositoryService.activateProcessDefinitionById(processId, true, new Date());
            log.info("已激活ID为:{}的流程", processId);
        }
        else if (state.equals("suspend"))
        {
            // 一并挂起流程实例
            repositoryService.suspendProcessDefinitionById(processId, true, new Date());
            log.info("已挂起ID为:{}的流程", processId);
        }
        return R.ok();
    }

    /**
     *
     * 获取流程节点
     *
     * @auther: Ace Lee
     * @date: 2019/3/7 16:27
     */
    @GetMapping("/{proDefId}/elements")
    public R listActivities(@PathVariable String proDefId)
    {
        List<Map<String, Object>> list = new ArrayList<>();
        BpmnModel model = repositoryService.getBpmnModel(proDefId);
        if (model != null)
        {
            Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();
            for (FlowElement e : flowElements)
            {
                log.info("flowelement id:" + e.getId() + "  name:" + e.getName() + "   class:"
                        + e.getClass().toString());
                if (e.getClass().toString().equalsIgnoreCase("class org.activiti.bpmn.model.StartEvent")
                        || e.getClass().toString().equalsIgnoreCase("class org.activiti.bpmn.model.UserTask"))
                {
                    Map<String, Object> element = new HashMap<>();
                    element.put(e.getId(), e.getName());
                    list.add(element);
                }
            }
        }
        return R.ok().put("rows", list);
    }
}

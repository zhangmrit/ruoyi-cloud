package com.ruoyi.activiti.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.exception.RuoyiException;

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
    private RepositoryService          repositoryService;

    @Autowired
    private RuntimeService             runtimeService;

    @Autowired
    private HistoryService             historyService;

    @Autowired
    private TaskService                taskService;

    @Autowired
    private ProcessEngineConfiguration processEngineConfiguration;

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
     * proc 实时高亮流程图
     * 
     * @param id 流程实例ID
     * @param response
     * @author zmr
     */
    @RequestMapping(value = "highlightImg/{id}")
    public void getHighlightImg(@PathVariable String id, HttpServletResponse response)
    {
        InputStream inputStream = null;
        ProcessInstance pi = null;
        String picName = "";
        // 查询历史
        HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery().processInstanceId(id)
                .singleResult();
        if (hpi.getEndTime() != null)
        {
            // 已经结束流程获取原图
            ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
                    .processDefinitionId(hpi.getProcessDefinitionId()).singleResult();
            picName = pd.getDiagramResourceName();
            inputStream = repositoryService.getResourceAsStream(pd.getDeploymentId(), pd.getDiagramResourceName());
        }
        else
        {
            pi = runtimeService.createProcessInstanceQuery().processInstanceId(id).singleResult();
            BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
            List<String> highLightedActivities = new ArrayList<String>();
            // 高亮任务节点
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(id).list();
            for (Task task : tasks)
            {
                highLightedActivities.add(task.getTaskDefinitionKey());
            }
            List<String> highLightedFlows = new ArrayList<String>();
            ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
            inputStream = diagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivities, highLightedFlows,
                    "宋体", "宋体", "宋体", null, 1.0);
            picName = pi.getName() + ".png";
        }
        try
        {
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(picName, "UTF-8"));
            byte[] b = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(b, 0, 1024)) != -1)
            {
                response.getOutputStream().write(b, 0, len);
            }
            response.flushBuffer();
        }
        catch (IOException e)
        {
            log.error(e.toString());
            throw new RuoyiException("读取流程图片失败");
        }
    }
}

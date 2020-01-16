package com.ruoyi.activiti.controller;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.activiti.consts.ActivitiConstant;
import com.ruoyi.activiti.cover.ICustomProcessDiagramGenerator;
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
     * @param procInstId 流程实例ID
     * @param response
     * @author zmr
     */
    @RequestMapping(value = "highlightImg/{procInstId}")
    public void getHighlightImg(@PathVariable String procInstId, HttpServletResponse response)
    {
        if (StringUtils.isBlank(procInstId))
        {
            log.error("参数为空");
        }
        HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(procInstId).singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity) repositoryService
                .getProcessDefinition(processInstance.getProcessDefinitionId());
        List<HistoricActivityInstance> highLightedActivitList = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(procInstId).orderByHistoricActivityInstanceId().asc().list();
        // 高亮环节id集合
        List<String> highLightedActivitis = new ArrayList<String>();
        // 高亮线路id集合
        List<String> highLightedFlows = getHighLightedFlows(definitionEntity, highLightedActivitList);
        for (HistoricActivityInstance tempActivity : highLightedActivitList)
        {
            String activityId = tempActivity.getActivityId();
            highLightedActivitis.add(activityId);
        }
        Set<String> currIds = runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).list()
                .stream().map(e -> e.getActivityId()).collect(Collectors.toSet());
        ICustomProcessDiagramGenerator diagramGenerator = (ICustomProcessDiagramGenerator) processEngineConfiguration
                .getProcessDiagramGenerator();
        InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitis,
                highLightedFlows, "宋体", "宋体", "宋体", null, 1.0,
                new Color[]{ActivitiConstant.COLOR_NORMAL, ActivitiConstant.COLOR_CURRENT}, currIds);
        try
        {
            // 输出资源内容到相应对象
            byte[] b = new byte[1024];
            int len;
            while ((len = imageStream.read(b, 0, 1024)) != -1)
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

    /**
     * 获取需要高亮的线
     * 
     * @param processDefinitionEntity
     * @param historicActivityInstances
     * @return
     */
    private List<String> getHighLightedFlows(ProcessDefinitionEntity processDefinitionEntity,
            List<HistoricActivityInstance> historicActivityInstances)
    {
        List<String> highFlows = new ArrayList<>();// 用以保存高亮的线flowId
        for (int i = 0; i < historicActivityInstances.size() - 1; i++)
        {
            // 对历史流程节点进行遍历
            // 获取当前历史节点
            HistoricActivityInstance currentActivityInstance = historicActivityInstances.get(i);
            // 得到节点定义的详细信息
            ActivityImpl activityImpl = processDefinitionEntity.findActivity(currentActivityInstance.getActivityId());
            // 用以保存后需开始时间相同的节点
            List<ActivityImpl> sameStartTimeNodes = new ArrayList<>();
            ActivityImpl sameActivityImpl1 = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i + 1).getActivityId());
            // 将后面第一个节点放在时间相同节点的集合里
            sameStartTimeNodes.add(sameActivityImpl1);
            /**
             * 遍历outgoingFlows并找到已流转的 满足如下条件认为已流转：
             * 1.当前节点是并行网关或兼容网关，则通过outgoingFlows能够在历史活动中找到的全部节点均为已流转
             * 2.当前节点是以上两种类型之外的，通过outgoingFlows查找到的时间最早的流转节点视为有效流转
             * (第2点有问题，有过驳回的，会只绘制驳回的流程线，通过走向下一级的流程线没有高亮显示)
             */
            // if
            // ("parallelGateway".equals(currentActivityInstance.getActivityType())
            // ||
            // "inclusiveGateway".equals(currentActivityInstance.getActivityType()))
            // {
            // }
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++)
            {
                HistoricActivityInstance activityImpl1 = historicActivityInstances.get(j);// 后续第一个节点
                HistoricActivityInstance activityImpl2 = historicActivityInstances.get(j + 1);// 后续第二个节点
                if (Math.abs(activityImpl1.getStartTime().getTime() - activityImpl2.getStartTime().getTime()) < 200)
                {
                    // if
                    // (activityImpl1.getStartTime().equals(activityImpl2.getStartTime()))
                    // {
                    // 如果第一个节点和第二个节点开始时间相同保存
                    ActivityImpl sameActivityImpl2 = processDefinitionEntity
                            .findActivity(activityImpl2.getActivityId());
                    sameStartTimeNodes.add(sameActivityImpl2);
                }
                else
                {
                    // 有不相同跳出循环
                    break;
                }
            }
            // 取出节点的所有出去的线
            List<PvmTransition> pvmTransitions = activityImpl.getOutgoingTransitions();
            for (PvmTransition pvmTransition : pvmTransitions)
            {
                // 对所有的线进行遍历
                ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition.getDestination();
                // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                if (sameStartTimeNodes.contains(pvmActivityImpl))
                {
                    highFlows.add(pvmTransition.getId());
                    break;
                }
            }
        }
        return highFlows;
    }
}

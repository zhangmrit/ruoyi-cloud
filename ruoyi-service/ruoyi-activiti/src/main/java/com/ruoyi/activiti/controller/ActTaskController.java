/*
 * @(#)ActTaskController.java 2020年1月7日 下午6:15:46
 * Copyright 2020 zmr, Inc. All rights reserved. 
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ruoyi.activiti.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.ruoyi.activiti.consts.ActivitiConstant;
import com.ruoyi.activiti.domain.BizAudit;
import com.ruoyi.activiti.domain.BizBusiness;
import com.ruoyi.activiti.service.IBizAuditService;
import com.ruoyi.activiti.service.IBizBusinessService;
import com.ruoyi.activiti.vo.HiTaskVo;
import com.ruoyi.activiti.vo.RuTask;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.feign.RemoteUserService;

import cn.hutool.core.util.StrUtil;

/**
 * <p>File：ActTaskController.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020 2020年1月7日 下午6:15:46</p>
 * <p>Company: zmrit.com </p>
 * @author zmr
 * @version 1.0
 */
@RestController
@RequestMapping("task")
public class ActTaskController extends BaseController
{
    @Autowired
    private TaskService         taskService;

    @Autowired
    private IBizAuditService    bizAuditService;

    @Autowired
    private RuntimeService      runtimeService;

    @Autowired
    private RemoteUserService   remoteUserService;

    @Autowired
    private IBizBusinessService businessService;

    /**
     * task待办
     * 
     * @return
     * @author zmr
     */
    @RequestMapping(value = "ing")
    public R ing(RuTask ruTask, PageDomain page)
    {
        List<RuTask> list = new ArrayList<>();
        Long userId = getCurrentUserId();
        TaskQuery query = taskService.createTaskQuery().taskCandidateOrAssigned(userId + "").orderByTaskCreateTime()
                .desc();
        if (StrUtil.isNotBlank(ruTask.getProcessDefKey()))
        {
            query.processDefinitionKey(ruTask.getProcessDefKey());
        }
        long count = query.count();
        int first = (page.getPageNum() - 1) * page.getPageSize();
        List<Task> taskList = query.listPage(first, page.getPageSize());
        // 转换vo
        taskList.forEach(e -> {
            RuTask rt = new RuTask(e);
            List<IdentityLink> identityLinks = runtimeService.getIdentityLinksForProcessInstance(rt.getProcInstId());
            for (IdentityLink ik : identityLinks)
            {
                // 关联发起人
                if ("starter".equals(ik.getType()) && StrUtil.isNotBlank(ik.getUserId()))
                {
                    rt.setApplyer(
                            remoteUserService.selectSysUserByUserId(Long.parseLong(ik.getUserId())).getUserName());
                }
            }
            // 关联业务key
            ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(rt.getProcInstId())
                    .singleResult();
            rt.setBusinessKey(pi.getBusinessKey());
            rt.setProcessName(pi.getName());
            rt.setProcessDefKey(pi.getProcessDefinitionKey());
            rt.setProcessDefName(pi.getProcessDefinitionName());
            list.add(rt);
        });
        Map<String, Object> map = Maps.newHashMap();
        map.put("rows", list);
        map.put("pageNum", page.getPageNum());
        map.put("total", count);
        return R.ok(map);
    }

    /**
     * task 已办
     * 
     * @param ruTask
     * @param page
     * @return
     * @author zmr
     */
    @RequestMapping(value = "done")
    public R done(HiTaskVo hiTaskVo)
    {
        startPage();
        hiTaskVo.setAuditorId(getCurrentUserId());
        hiTaskVo.setDeleteReason(ActivitiConstant.REASON_COMPLETED);
        return result(bizAuditService.getHistoryTaskList(hiTaskVo));
    }

    /**
     * task 流转历史
     * 
     * @param hiTaskVo
     * @return
     * @author zmr
     */
    @RequestMapping(value = "flow")
    public R flow(HiTaskVo hiTaskVo)
    {
        startPage();
        return result(bizAuditService.getHistoryTaskList(hiTaskVo));
    }

    /**
     * 审批
     * 
     * @param bizAudit
     * @return
     * @author zmr
     */
    @PostMapping("audit")
    public R audit(@RequestBody BizAudit bizAudit)
    {
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("result", bizAudit.getResult());
        // 审批
        taskService.complete(bizAudit.getTaskId(), variables);
        SysUser user = remoteUserService.selectSysUserByUserId(getCurrentUserId());
        bizAudit.setAuditor(user.getUserName() + "-" + user.getLoginName());
        bizAudit.setAuditorId(user.getUserId());
        bizAuditService.insertBizAudit(bizAudit);
        BizBusiness bizBusiness = new BizBusiness().setId(bizAudit.getBusinessKey())
                .setProcInstId(bizAudit.getProcInstId());
        businessService.setAuditor(bizBusiness, bizAudit.getResult(), getCurrentUserId());
        return R.ok();
    }

    @PostMapping("audit/batch")
    public R auditBatch(@RequestBody BizAudit bizAudit)
    {
        SysUser user = remoteUserService.selectSysUserByUserId(getCurrentUserId());
        for (String taskId : bizAudit.getTaskIds())
        {
            Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
            ProcessInstance pi = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(task.getProcessInstanceId()).singleResult();
            BizBusiness bizBusiness = businessService.selectBizBusinessById(pi.getBusinessKey());
            if (null != bizBusiness)
            {
                Map<String, Object> variables = Maps.newHashMap();
                variables.put("result", bizAudit.getResult());
                // 审批
                taskService.complete(taskId, variables);
                // 构建插入审批记录
                BizAudit audit = new BizAudit().setTaskId(taskId).setResult(bizAudit.getResult())
                        .setProcName(bizBusiness.getProcName()).setProcDefKey(bizBusiness.getProcDefKey())
                        .setApplyer(bizBusiness.getApplyer()).setAuditor(user.getUserName() + "-" + user.getLoginName())
                        .setAuditorId(user.getUserId());
                bizAuditService.insertBizAudit(audit);
                businessService.setAuditor(bizBusiness, audit.getResult(), getCurrentUserId());
            }
        }
        return R.ok();
    }

    /**
     *  remove审批记录 逻辑删除
     */
    @PostMapping("remove")
    public R remove(String ids)
    {
        return toAjax(bizAuditService.deleteBizAuditLogic(ids));
    }
}

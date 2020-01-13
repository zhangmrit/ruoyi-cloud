/*
 * @(#)BizBusinessServiceImpl.java 2020年1月6日 下午3:38:49
 * Copyright 2020 zmr, Inc. All rights reserved. 
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ruoyi.activiti.service.impl;

import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.ruoyi.activiti.domain.BizBusiness;
import com.ruoyi.activiti.mapper.BizBusinessMapper;
import com.ruoyi.activiti.service.IBizBusinessService;

import tk.mybatis.mapper.entity.Example;

/**
 * <p>File：BizBusinessServiceImpl.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020 2020年1月6日 下午3:38:49</p>
 * <p>Company: zmrit.com </p>
 * @author zmr
 * @version 1.0
 */
@Service
public class BizBusinessServiceImpl implements IBizBusinessService
{
    @Autowired
    private BizBusinessMapper businessMapper;
    
    @Autowired
    private RuntimeService      runtimeService;

    @Autowired
    private IdentityService     identityService;

    @Autowired
    private TaskService         taskService;

    /**
     * 查询流程业务
     * 
     * @param id 流程业务ID
     * @return 流程业务
     */
    @Override
    public BizBusiness selectBizBusinessById(String id)
    {
        return businessMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询流程业务列表
     * 
     * @param bizBusiness 流程业务
     * @return 流程业务
     */
    @Override
    public List<BizBusiness> selectBizBusinessList(BizBusiness bizBusiness)
    {
        return businessMapper.select(bizBusiness);
    }

    /**
     * 新增流程业务
     * 
     * @param bizBusiness 流程业务
     * @return 结果
     */
    @Override
    public int insertBizBusiness(BizBusiness bizBusiness)
    {
        return businessMapper.insertSelective(bizBusiness);
    }

    /**
     * 修改流程业务
     * 
     * @param bizBusiness 流程业务
     * @return 结果
     */
    @Override
    public int updateBizBusiness(BizBusiness bizBusiness)
    {
        return businessMapper.updateByPrimaryKeySelective(bizBusiness);
    }

    /**
     * 删除流程业务对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBizBusinessByIds(String ids)
    {
        return businessMapper.deleteByIds(ids);
    }

    /**
     * 删除流程业务信息
     * 
     * @param id 流程业务ID
     * @return 结果
     */
    public int deleteBizBusinessById(Long id)
    {
        return businessMapper.deleteByPrimaryKey(id);
    }

    /* (non-Javadoc)
     * @see com.ruoyi.activiti.service.IBizBusinessService#deleteBizBusinessLogic(java.lang.String)
     */
    @Override
    public int deleteBizBusinessLogic(String ids)
    {
        Example example = new Example(BizBusiness.class);
        example.createCriteria().andIn("id", Lists.newArrayList(ids.split(",")));
        return businessMapper.updateByExampleSelective(new BizBusiness().setDelFlag(true), example);
    }

    /* (non-Javadoc)
     * @see com.ruoyi.activiti.service.IBizBusinessService#startProcess(com.ruoyi.activiti.domain.BizBusiness, java.util.Map)
     */
    @Override
    public void startProcess(BizBusiness business, Map<String, Object> variables)
    {
        // 启动流程用户
        identityService.setAuthenticatedUserId(business.getUserId().toString());
        // 启动流程 需传入业务表id变量
        ProcessInstance pi = runtimeService.startProcessInstanceById(business.getProcDefId(),
                business.getId().toString(), variables);
        // 设置流程实例名称
        runtimeService.setProcessInstanceName(pi.getId(), business.getTitle());
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
        updateBizBusiness(new BizBusiness().setId(business.getId()).setProcInstId(pi.getId())
                .setCurrentTask(task.getName()).setProcDefKey(pi.getProcessDefinitionKey()));
    }
}

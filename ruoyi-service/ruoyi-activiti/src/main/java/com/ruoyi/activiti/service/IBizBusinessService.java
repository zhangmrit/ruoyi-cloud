/*
 * @(#)IBizBusinessService.java 2020年1月6日 下午3:38:40
 * Copyright 2020 zmr, Inc. All rights reserved. 
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ruoyi.activiti.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.activiti.domain.BizBusiness;

/**
 * <p>File：IBizBusinessService.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020 2020年1月6日 下午3:38:40</p>
 * <p>Company: zmrit.com </p>
 * @author zmr
 * @version 1.0
 */
public interface IBizBusinessService
{
    /**
     * 查询流程业务
     * 
     * @param id 流程业务ID
     * @return 流程业务
     */
    public BizBusiness selectBizBusinessById(String id);

    /**
     * 查询流程业务列表
     * 
     * @param bizBusiness 流程业务
     * @return 流程业务集合
     */
    public List<BizBusiness> selectBizBusinessList(BizBusiness bizBusiness);

    /**
     * 新增流程业务
     * 
     * @param bizBusiness 流程业务
     * @return 结果
     */
    public int insertBizBusiness(BizBusiness bizBusiness);

    /**
     * 修改流程业务
     * 
     * @param bizBusiness 流程业务
     * @return 结果
     */
    public int updateBizBusiness(BizBusiness bizBusiness);

    /**
     * 批量删除流程业务
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizBusinessByIds(String ids);

    /**
     * logic remove 逻辑删除
     * 
     * @param ids
     * @return
     * @author zmr
     */
    public int deleteBizBusinessLogic(String ids);

    /**
     * 删除流程业务信息
     * 
     * @param id 流程业务ID
     * @return 结果
     */
    public int deleteBizBusinessById(Long id);

    /**
     * start 启动流程
     * 
     * @param business 业务对象，必须包含id,title,userId,procDefId属性
     * @param variables 启动流程需要的变量
     * @author zmr
     */
    void startProcess(BizBusiness business, Map<String, Object> variables);

    /**
     * check 检查负责人
     * 
     * @param business 业务对象，必须包含id,procInstId属性
     * @param result 审批结果
     * @param currentUserId 当前操作用户 可能是发起人或者任务处理人
     * @return
     * @author zmr
     */
    public int setAuditor(BizBusiness business, int result, long currentUserId);

}

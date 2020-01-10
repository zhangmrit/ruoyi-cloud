/*
 * @(#)ActBusinessMapper.java 2020年1月6日 下午3:38:12
 * Copyright 2020 zmr, Inc. All rights reserved. 
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ruoyi.activiti.mapper;

import java.util.List;

import com.ruoyi.activiti.domain.BizAudit;
import com.ruoyi.activiti.vo.HiTaskVo;
import com.ruoyi.common.core.dao.BaseMapper;

/**
 * <p>File：BizAuditMapper.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020 2020年1月6日 下午3:38:12</p>
 * <p>Company: zmrit.com </p>
 * @author zmr
 * @version 1.0
 */
public interface BizAuditMapper extends BaseMapper<BizAudit>
{
    List<HiTaskVo> getHistoryTaskList(HiTaskVo hiTaskVo);

    /**
     * logic删除
     * @param ids
     * @return
     * @author zmr
     */
    int deleteLogic(String[] ids);
}

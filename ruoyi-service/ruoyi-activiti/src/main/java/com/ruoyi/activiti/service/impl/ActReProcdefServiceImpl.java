/*
 * @(#)ActReProcdefServiceImpl.java 2020年1月6日 上午10:22:35
 * Copyright 2020 zmr, Inc. All rights reserved. 
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ruoyi.activiti.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.activiti.domain.ActReProcdef;
import com.ruoyi.activiti.mapper.ActReProcdefMapper;
import com.ruoyi.activiti.service.IActReProcdefService;

/**
 * <p>File：ActReProcdefServiceImpl.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020 2020年1月6日 上午10:22:35</p>
 * <p>Company: zmrit.com </p>
 * @author zmr
 * @version 1.0
 */
@Service
public class ActReProcdefServiceImpl implements IActReProcdefService
{
    @Autowired
    private ActReProcdefMapper procdefMapper;

    /* (non-Javadoc)
     * @see com.ruoyi.activiti.service.IActReProcdefService#selectList(com.ruoyi.activiti.domain.ActReProcdef)
     */
    @Override
    public List<ActReProcdef> selectList(ActReProcdef actReProcdef)
    {
        return procdefMapper.select(actReProcdef);
    }
}

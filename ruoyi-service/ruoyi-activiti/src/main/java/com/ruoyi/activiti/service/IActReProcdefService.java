/*
 * @(#)IActReProcdefService.java 2020年1月6日 上午10:21:18
 * Copyright 2020 zmr, Inc. All rights reserved. 
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ruoyi.activiti.service;

import java.util.List;

import com.ruoyi.activiti.domain.ActReProcdef;

/**
 * <p>File：IActReProcdefService.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020 2020年1月6日 上午10:21:18</p>
 * <p>Company: zmrit.com </p>
 * @author zmr
 * @version 1.0
 */
public interface IActReProcdefService
{
    public List<ActReProcdef> selectList(ActReProcdef actReProcdef);
}

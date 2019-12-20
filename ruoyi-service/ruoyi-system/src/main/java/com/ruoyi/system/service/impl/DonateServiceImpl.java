/*
 * @(#)DonateServiceImpl.java 2019年12月20日 下午2:13:36
 * Copyright 2019 zmr, Inc. All rights reserved. 
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ruoyi.system.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.domain.Donate;
import com.ruoyi.system.mapper.DonateMapper;
import com.ruoyi.system.service.IDonateService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * <p>File：DonateServiceImpl.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2019 2019年12月20日 下午2:13:36</p>
 * <p>Company: zmrit.com </p>
 * @author zmr
 * @version 1.0
 */
@Service
public class DonateServiceImpl implements IDonateService
{
    @Autowired
    private DonateMapper donateMapper;

    @Override
    public List<Donate> selectDistrictsList(Donate donate)
    {
        Example example = new Example(Donate.class);
        Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(donate.getNick()))
        {
            criteria.andLike("nick", "%" + donate.getNick() + "%");
        }
        if (null != donate.getCanal())
        {
            criteria.andEqualTo("canal", donate.getCanal());
        }
        if (StringUtils.isNotBlank(donate.getBeginTime()))
        {
            criteria.andGreaterThanOrEqualTo("createTime", donate.getBeginTime());
        }
        if (StringUtils.isNotBlank(donate.getEndTime()))
        {
            criteria.andLessThanOrEqualTo("createTime", donate.getEndTime());
        }
        return donateMapper.selectByExample(example);
    }
}

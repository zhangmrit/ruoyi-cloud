/*
 * @(#)DonateController.java 2019年12月20日 下午2:32:25
 * Copyright 2019 zmr, Inc. All rights reserved. 
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ruoyi.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.domain.Donate;
import com.ruoyi.system.service.IDonateService;

/**
 * <p>File：DonateController.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2019 2019年12月20日 下午2:32:25</p>
 * <p>Company: zmrit.com </p>
 * @author zmr
 * @version 1.0
 */
@RestController
@RequestMapping("donate")
public class DonateController extends BaseController
{
    @Autowired
    private IDonateService donateService;

    @GetMapping("list")
    public R list(Donate donate)
    {
        startPage();
        List<Donate> list = donateService.selectDistrictsList(donate);
        return result(list);
    }
}
